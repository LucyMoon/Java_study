package kr.hs.dgsw.network.test01.n2208.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientThread extends Thread {
	private static List<ClientThread> ClientList = (List<ClientThread>) Collections
			.synchronizedList(new ArrayList<ClientThread>());

	private Client_Accept ca = null;
	private String address = null;

	public ClientThread(String address, Client_Accept ca) {
		this.ca = ca;
		this.address = address;
		ClientList.add(this);
//		notMeMessage("[Start]\r\n"
//				+ "Type::LogIn\r\n"
//				+ "FromNick::"+address+"\r\n"
//				+ "[End]");
	}

//	public boolean containsNickname(String nickname) {
//		for(ClientThread cttmp:ClientList) {
//			if(cttmp.nickname.equals(nickname)) {
//				return true;
//			}
//		}
//		return false;
//	}

//	public void sendMessage(String msg) {
//		ca.sendMessage(msg);
//	}

//	// 전체에게 메시지 발송
//	public void allMessage(String msg) {
//		for(ClientThread cttmp:ClientList) {
//			cttmp.sendMessage(msg);			// 출력
//		}
//	}

	// 특정인에게 발송
	public void message(String msg, String address) {
		for (ClientThread cttmp : ClientList) {
			if (cttmp.address.equals(address))
				ca.sendMessage(msg); // 출력
		}
	}

//	// 나 제외하고 발송
//	public void notMeMessage(String msg) {
//		for(ClientThread cttmp:ClientList) {
//			if(cttmp != this)
//				cttmp.sendMessage(msg);			// 출력
//		}
//	}

	public void run() {
		try {
			boolean loginboolean=true;
			while(loginboolean) {
				String brmsg = ca.readMessage();	
				
				String msgType = "";				
				String msgFilePath = "";
				String msgFileName = "";
				String msgDownFileName= "";
				String ID = "";
				String PW = "";
				String Size = "";
				String Fname = "";
				
				if(brmsg.equals("[Start]")) {
					brmsg = ca.readMessage();
					String[] brmsgarr = brmsg.split("::");
					if(brmsgarr[0].equals("Type")) {
						msgType = brmsgarr[1];
					} else {
						System.out.println(this.address + ": 발송 메시지가 이상합니다.");
						
						continue;
					}
					
					while(!(brmsg = ca.readMessage()).equals("[End]")) {
						brmsgarr = brmsg.split("::");
						switch(brmsgarr[0]) {
						case "DownFilename":
							msgDownFileName = brmsgarr[1]; break;
						case "ID":
							ID = brmsgarr[1]; break;
						case "PW":
							PW = brmsgarr[1]; break;
						case "Size":
							Size = brmsgarr[1]; break;
						default:
							break;
						}
					}
					System.out.println(msgType);
					
					switch(msgType) {
						case "Login":
							if(ID.equals("admin") && PW.equals("1234")) {
								message("[Start]\r\n"
										+ "Type::Login\r\n"
										+ "CheckLogin::" + "OK" + "\r\n"
										+ "[End]",
										this.address);
							} else {
								message("[Start]\r\n"
										+ "Type::Login\r\n"
										+ "CheckLogin::" + "Fail" + "\r\n"
										+ "[End]",
										this.address);
							}
							break;
						case "Upload":
							Fname = ca.readFile();
							File fl = new File("D:/temp/"+Fname);
//							System.out.println(fl.getAbsolutePath());
							if(fl.exists()) {
								message("[Start]\r\n"
										+ "Type::CheckUpload\r\n"
										+ "Check::NO\r\n"
										+ "[End]",
										this.address);
							}else {
								message("[Start]\r\n"
										+ "Type::CheckUpload\r\n"
										+ "Check::YES\r\n"
										+ "[End]",
										this.address);
							}
							break;
						case "UploadOK":
							ca.UploadFile(Fname, Size);
							
								message("[Start]\r\n"
										+ "Type::Upload\r\n"
										+ "FName::" + Fname + "\r\n"
										+ "[End]",
										this.address);
							break;
						case "FileList":
							message("[Start]\r\n"
									+ "Type::FileList\r\n"
									+ "FileName::" + getFileNameList() + "\r\n"
									+ "FileSize::" + getFileSizeList() + "\r\n"
									+ "[End]",
									this.address);
							break;
						case "DownLoad":
							fl = new File("D:/temp/"+msgDownFileName);
							if(fl.exists()) {
								FileInputStream fis = new FileInputStream(fl);
								BufferedInputStream bis = new BufferedInputStream(fis);
								OutputStream os = null;
								try {
									os = ca.sc.getOutputStream();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedOutputStream bor = new BufferedOutputStream(os);
								DataOutputStream dos = new DataOutputStream(bor);
								dos.writeUTF(msgDownFileName);
								dos.flush();
								long tempSize = fl.length();
								
								message("[Start]\r\n"
										+ "Type::FileDown\r\n"
										+ "Size::"+fl.length() + "\r\n"
										+ "[End]",
										this.address);
								
								int readsize = 0;
								byte[] bytes = new byte[1024];
								
								while((readsize = fis.read(bytes)) <= tempSize) {
									tempSize -= readsize;
									if(tempSize == 0) {
										break;
									}
									dos.write(bytes, 0, readsize);
								}
								dos.flush();
							} else {
								System.out.println("경로의 파일 없음");
							}
							
							break;
						case "Logout":
							this.ca.sc.close();
							ClientList.remove(this);
							break;
//						case "Whisper":
//							if(containsNickname(msgToNick)) {
//								message("[Start]\r\n"
//										+ "Type::Whisper\r\n"
//										+ "FromNick::"+ this.nickname +"\r\n"
//										+ "Message::\r\n"
//										+ msgMessage
//										+ "::Message\r\n"
//										+ "[End]", msgToNick);
//								sendMessage("[Start]\r\n"
//										+ "Type::WhisperOK\r\n"
//										+ "[End]");
//							} else {
//								sendMessage("[Start]\r\n"
//										+ "Type::WhisperWhithout\r\n"
//										+ "[End]");
//							}
//							break;
//						case "AllMessage":
//							notMeMessage("[Start]\r\n"
//									+ "Type::AllMessage\r\n"
//									+ "FromNick::"+ this.nickname +"\r\n"
//									+ "Message::\r\n"
//									+ msgMessage
//									+ "::Message\r\n"
//									+ "[End]");
//							break;
//						case "NickList":
//							String tmpmsg="[Start]\r\n"
//									+ "Type::NickList\r\n"
//									+ "JoinCount::" + ClientList.size() + "\r\n"
//									+ "Message::\r\n";
//							for(ClientThread tmpcl:ClientList) {
//								tmpmsg += tmpcl.nickname +"\r\n";
//							}
//							tmpmsg += "::Message\r\n"
//									+ "[End]";
//							sendMessage(tmpmsg);
//							break;
					}
					
					
				}
			}
		} catch(Exception e) {
			System.out.println("연결 종료");
			ClientList.remove(this);
		}
	}

	private ArrayList<String> getFileSizeList() {
		File dir = new File("D:\\temp");
		File[] listsFiles = dir.listFiles();
		ArrayList<String> FileSize = new ArrayList<String>();
		for (File file : listsFiles) {
			if (file != null) {
				long Fsize = file.length();
				int countsize = 0;
				String Size = "B";
				while (Fsize >= 1024) {
					Fsize /= 1024;
					countsize++;
				}
				switch (countsize) {
				case 1:
					Size = "KB";
				case 2:
					Size = "MB";
				case 3:
					Size = "GB";
				case 4:
					Size = "TB";
				default:
					Size = "B";
				}

				FileSize.add(Long.toString(Fsize) + Size);
			}
		}
		return FileSize;
	}

	private ArrayList<String> getFileNameList() {
		File dir = new File("D:\\temp");
		File[] listsFiles = dir.listFiles();
		ArrayList<String> FileName = new ArrayList<String>();
		for (File file : listsFiles) {
			if (file != null) {
				FileName.add(file.getName());
			}
		}
		return FileName;
	}
}