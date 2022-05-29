package kr.hs.dgsw.network.test01.n2208.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class InputMessage extends Thread {
	private Socket sc = null;

	
	public InputMessage(Socket sc) {
		this.sc = sc;
	}
	
	public void run() {
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
						case "Fname":
							Fname = brmsgarr[1];
							break;
						default:
							break;
						}
					}
					
					switch(msgType) {
						case "FileList":
							for(int i = 0; i < FileName.length; i++) {
								System.out.println(FileName[i] + " " + FileSize[i]);
							}
							break;
						case "Upload":
							System.out.println(Fname + "파일을 업로드 하셨습니다.");
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