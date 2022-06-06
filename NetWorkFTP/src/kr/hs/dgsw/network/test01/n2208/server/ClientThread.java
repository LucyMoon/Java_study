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
	
	private String Path = "D:/temp/";

	public ClientThread(String address, Client_Accept ca) {
		this.ca = ca;
		this.address = address;
		ClientList.add(this);
	}

	public void message(String msg, String address) {
		for (ClientThread cttmp : ClientList) {
			if (cttmp.address.equals(address))
				ca.sendMessage(msg); // 출력
		}
	}

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
				String SendFilePath = "";
				String FN = "";
				String ET = "";
				msgType = "";
				
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
						case "Filename":
							Fname = brmsgarr[1]; break;
						case "SendFilePath":
							SendFilePath = brmsgarr[1];break;
						case "FN":
							FN = brmsgarr[1];break;
						case "ET":
							ET = brmsgarr[1]; break;
						default:
							break;
						}
					}
					
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
						

							File fl = new File(Path+FN+ET);
							

							if(fl.exists()) {
								message("[Start]\r\n"
										+ "Type::CheckUpload\r\n"
										+ "Check::NO\r\n"
										+ "CheckFilePath::"+SendFilePath+"\r\n"
										+ "CheckFN::"+FN+"\r\n"
										+ "[End]",
										this.address);
							}else {
								message("[Start]\r\n"
										+ "Type::CheckUpload\r\n"
										+ "Check::YES\r\n"
										+ "CheckFilePath::"+SendFilePath+"\r\n"
										+ "CheckFN::"+FN+"\r\n"
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
							fl = new File(Path+msgDownFileName);
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
								long tempSize = fl.length();

								message("[Start]\r\n"
										+ "Type::FileDown\r\n"
										+ "Filename::"+fl.getName() + "\r\n"
										+ "Size::"+fl.length() + "\r\n"
										+ "[End]",
										this.address);
								
								int readsize = 0;
								byte[] bytes = new byte[1024 * 8];
								
								while((readsize = fis.read(bytes)) <= tempSize) {
									tempSize -= readsize;
									dos.write(bytes, 0, readsize);
									if(tempSize == 0) {
										break;
									}
								}
								dos.flush();
							} else {
								message("[Start]\r\n"
										+ "Type::NoFile\r\n"
										+ "[End]",
										this.address);
							}
							
							break;
						case "Logout":
							this.ca.sc.close();
							ClientList.remove(this);
							break;
					}	
				}
			}
		} catch(Exception e) {
			System.out.println("연결 종료");
			ClientList.remove(this);
		}
	}

	private ArrayList<String> getFileSizeList() {
		File dir = new File(Path);
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
					Size = "KB"; break;
				case 2:
					Size = "MB"; break;
				case 3:
					Size = "GB"; break;
				case 4:
					Size = "TB"; break;
				default:
					Size = "B"; break;
				}

				FileSize.add(Long.toString(Fsize) + Size);
			}
		}
		return FileSize;
	}

	private ArrayList<String> getFileNameList() {
		File dir = new File(Path);
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