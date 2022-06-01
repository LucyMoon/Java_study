package kr.hs.dgsw.network.test01.n2208.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class InputMessage extends Thread {
	private Socket sc = null;
	private OutputMessage om = null;
	

	
	public InputMessage(Socket sc, OutputMessage om) {
		this.sc = sc;
		this.om = om;
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);
		try {
			InputStream is = sc.getInputStream();		// 클라이언트 메시지 입력 받음
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			boolean loginboolean=true;
			while(loginboolean) {
				String brmsg = br.readLine();
				
				String msgType = "";	
				String msgFromNick = "";
				String msgBeforeNick = "";
				String msgAfterNick = "";
				String msgJoinCount = "";
				String msgMessage = "";
				String[] FileName = null;
				String[] FileSize = null;
				String Fname = null;
				String CheckLogin = "";
				String CheckFile = "";
				String Check = "";
				String Size = "";
				if(brmsg == null){
					loginboolean = false;
					interrupt();
					break;
				}
				if(brmsg.equals("[Start]")) {
					brmsg = br.readLine();
					String[] brmsgarr = brmsg.split("::");
					if(brmsgarr[0].equals("Type")) {
						msgType = brmsgarr[1];
					} else {
						System.out.println("수신 메시지가 이상합니다.");
						continue;
					}
					System.out.println(msgType);
					
					while(!(brmsg = br.readLine()).equals("[End]")) {
						brmsgarr = brmsg.split("::");
						switch(brmsgarr[0]) {
						case "FromNick":
							msgFromNick = brmsgarr[1]; break;
						case "BeforeNick":
							msgBeforeNick = brmsgarr[1]; break;
						case "AfterNick":
							msgAfterNick = brmsgarr[1]; break;
						case "JoinCount":
							msgJoinCount = brmsgarr[1]; break;
						case "FileName":
							String tempName = brmsgarr[1].substring(1, brmsgarr[1].length() - 1).replaceAll(" ", "");
							FileName = tempName.split(",");
							break;
						case "FileSize":
							String tempSize = brmsgarr[1].substring(1, brmsgarr[1].length() - 1).replaceAll(" ", "");
							FileSize = tempSize.split(",");
							break;
						case "FName":
							Fname = brmsgarr[1];
							break;
						case "CheckLogin":
							CheckLogin = brmsgarr[1]; break;
						case "CheckFile":
							CheckFile = brmsgarr[1]; break;
						case "Check":
							Check = brmsgarr[1]; break;
						case "Size":
							Size = brmsgarr[1];break;
						default:
							break;
						}
					}

					
					switch(msgType) {
						case "Login":
							if(CheckLogin.equals("OK")) {
								System.out.println("로그인 성공");
								om.Login();
							} else {
								System.out.println("ID또는 PW가 틀렸습니다.");
							}
							break;
						case "FileList":
							for(int i = 0; i < FileName.length; i++) {
								System.out.println(FileName[i] + " " + FileSize[i]);
							}
							break;
						case "CheckUpload":
							if(Check.equals("YES")) {
								om.UploadOK(); //얘가 오류인듯.......
								om.UploadFile();
								
							}else {
								System.out.println("파일이 이미 있습니다. 덮어 쓰시겠습니까?(Yes/No) : ");
								if(scan.nextLine().equals("Yes")) {
									om.UploadOK();
									om.UploadFile();
								}
							}
							break;
						case "FileDown":
							BufferedInputStream bir = new BufferedInputStream(is);
							DataInputStream dis = new DataInputStream(bir);
							String filename = dis.readUTF();
							FileOutputStream fos = new FileOutputStream("D:/down/" + filename);
							BufferedOutputStream bos = new BufferedOutputStream(fos);
							int TempSize = Integer.parseInt(Size);
							
							System.out.println(TempSize);
							int readsize = 0;
							byte[] bytes = new byte[1024 * 8];
							while((readsize = dis.read(bytes)) <= TempSize) {
								TempSize -= readsize;
								System.out.println(TempSize);
								if(TempSize == 0) {
									break;
								}
								fos.write(bytes, 0, readsize);
								fos.flush();
							}
							System.out.println(filename + "을 D:/down/로 다운로드 하셨습니다.");
							break;
						case "Upload":
							System.out.println(Fname + "파일을 업로드 하셨습니다.");
							break;
//						case "LogOutOK":
//							System.out.println("로그아웃하였습니다.");
//							loginboolean = false;
//							break;
//						case "LogOut":
//							System.out.println("[" + msgFromNick + "] 사용자가 로그아웃하였습니다.");
//							break;
//						case "NickNameOK":
//							System.out.println("닉네임을 [" + msgAfterNick + "]로 변경하였습니다.");
//							break;
//						case "NickNameOverlap":
//							System.out.println("닉네임을 [" + msgAfterNick + "]는 이미 사용중입니다.");
//							break;
//						case "NickNameInform":
//							System.out.println("[" + msgBeforeNick + "] 사용자가 닉네임을 [" + msgAfterNick + "]로 변경하였습니다.");
//							break;
//						case "Whisper":
//							System.out.println("[귓속말] " + msgFromNick + ": " + msgMessage);
//							break;
//						case "WhisperOK":
//							System.out.println("[귓속말] 전송 성공");
//							break;
//						case "WhisperWhithout":
//							System.out.println("[귓속말] 전송 실패 / 닉네임이 없습니다.");
//							break;
//						case "AllMessage":
//							System.out.println(msgFromNick + ": " + msgMessage);
//							break;
//						case "NickList":
//							System.out.println("접속자수: "+ msgJoinCount);
//							System.out.println("[접속자리스트]");
//							System.out.println(msgMessage);
//							System.out.println("[접속자리스트끝]");
//							break;
						default:
							break;
					}
				}
			}
		} catch(Exception e) {
			System.out.println("연결 종료");
			e.printStackTrace();
		}
	}
}