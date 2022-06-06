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
	private String DownPath = "D:\\down\\";
	

	
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
				String[] FileName = null;
				String[] FileSize = null;
				String Fname = null;
				String CheckLogin = "";
				String CheckFile = "";
				String Check = "";
				String Size = "";
				String Filename = "";
				String CheckFN = "";
				String CheckFilePath = "";
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
					
					while(!(brmsg = br.readLine()).equals("[End]")) {
						brmsgarr = brmsg.split("::");
						switch(brmsgarr[0]) {
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
						case "Filename":
							Filename = brmsgarr[1];break;
						case "CheckFilePath":
							CheckFilePath = brmsgarr[1];break;
						case "CheckFN" :
							CheckFN = brmsgarr[1];break;
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
								om.UploadOK(CheckFN);
								om.UploadFile(CheckFilePath);
								
							}else {
								System.out.println("파일이 이미 있습니다. 덮어 쓰시겠습니까?(Yes/No) : ");
								if(scan.nextLine().equals("Yes")) {
									om.UploadOK(CheckFN);
									om.UploadFile(CheckFilePath);
								}
							}
							break;
						case "FileDown":
							int TempSize = Integer.parseInt(Size);
							BufferedInputStream bir = new BufferedInputStream(is);
							DataInputStream dis = new DataInputStream(bir);
							try {
								FileOutputStream fos = new FileOutputStream(DownPath + Filename);
								
								int readsize = 0;
								byte[] bytes = new byte[1024 * 8];
								while((readsize = dis.read(bytes)) <= TempSize) {
									TempSize -= readsize;
									fos.write(bytes, 0, readsize);
//									System.out.println(TempSize);
									if(TempSize <= 0) {
										break;
									}
								}
								fos.flush();
								System.out.println(Filename + "을 D:/down/로 다운로드 하셨습니다.");
							
							
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
							
						case "NoFile":
							System.out.println("경로에 파일이 없습니다.");
						case "Upload":
							System.out.println(Fname + "파일을 업로드 하셨습니다.");
							break;
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