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
						System.out.println("���� �޽����� �̻��մϴ�.");
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
								System.out.println("�α��� ����");
								om.Login();
							} else {
								System.out.println("ID�Ǵ� PW�� Ʋ�Ƚ��ϴ�.");
							}
							break;
						case "FileList":
							for(int i = 0; i < FileName.length; i++) {
								System.out.println(FileName[i] + " " + FileSize[i]);
							}
							break;
						case "CheckUpload":
							if(Check.equals("YES")) {
								om.UploadOK(); //�갡 �����ε�.......
								om.UploadFile();
								
							}else {
								System.out.println("������ �̹� �ֽ��ϴ�. ���� ���ðڽ��ϱ�?(Yes/No) : ");
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
							System.out.println(filename + "�� D:/down/�� �ٿ�ε� �ϼ̽��ϴ�.");
							break;
						case "Upload":
							System.out.println(Fname + "������ ���ε� �ϼ̽��ϴ�.");
							break;
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