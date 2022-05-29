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
			InputStream is = sc.getInputStream();		// Ŭ���̾�Ʈ �޽��� �Է� ����
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
						System.out.println("���� �޽����� �̻��մϴ�.");
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
							System.out.println(Fname + "������ ���ε� �ϼ̽��ϴ�.");
//						case "LogOutOK":
//							System.out.println("�α׾ƿ��Ͽ����ϴ�.");
//							loginboolean = false;
//							break;
//						case "LogOut":
//							System.out.println("[" + msgFromNick + "] ����ڰ� �α׾ƿ��Ͽ����ϴ�.");
//							break;
//						case "NickNameOK":
//							System.out.println("�г����� [" + msgAfterNick + "]�� �����Ͽ����ϴ�.");
//							break;
//						case "NickNameOverlap":
//							System.out.println("�г����� [" + msgAfterNick + "]�� �̹� ������Դϴ�.");
//							break;
//						case "NickNameInform":
//							System.out.println("[" + msgBeforeNick + "] ����ڰ� �г����� [" + msgAfterNick + "]�� �����Ͽ����ϴ�.");
//							break;
//						case "Whisper":
//							System.out.println("[�ӼӸ�] " + msgFromNick + ": " + msgMessage);
//							break;
//						case "WhisperOK":
//							System.out.println("[�ӼӸ�] ���� ����");
//							break;
//						case "WhisperWhithout":
//							System.out.println("[�ӼӸ�] ���� ���� / �г����� �����ϴ�.");
//							break;
//						case "AllMessage":
//							System.out.println(msgFromNick + ": " + msgMessage);
//							break;
//						case "NickList":
//							System.out.println("�����ڼ�: "+ msgJoinCount);
//							System.out.println("[�����ڸ���Ʈ]");
//							System.out.println(msgMessage);
//							System.out.println("[�����ڸ���Ʈ��]");
//							break;
						default:
							break;
					}
				}
			}
		} catch(Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
	}
}