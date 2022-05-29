package kr.hs.dgsw.network.test01.n2208.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputMessage extends Thread {
	private Socket sc = null;
	
	public OutputMessage(Socket sc) {
		this.sc = sc;
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);

		OutputStream os = null;
		
		
		try {
			os = sc.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedOutputStream bor = new BufferedOutputStream(os);
		DataOutputStream dos = new DataOutputStream(bor);
		
		PrintWriter pw = new PrintWriter(os, true);
		
		try {
			String msg = "";
			String sandmsg ="";
			boolean loginboolean = true;
			while((loginboolean == true) && ((msg=scan.nextLine())!=null) ){
				if(msg.substring(0,1).equals("/")){
					String type = "";
					if(msg.indexOf(' ') != -1) {
						type = msg.substring(1, msg.indexOf(' '));
						msg = msg.substring(msg.indexOf(' '));
					} else {
						type = msg.substring(1);
					}
					switch(type) {
						case "파일목록":
							sandmsg = "[Start]\r\n"
									+"Type::FileList\r\n"
									+"[End]";
							break;
						case "업로드":
							if(msg.indexOf(' ') != -1) {
								String path = msg.substring(0, msg.substring(1, msg.length()).indexOf(' ')+1).trim();
								File fl = new File(path);
								if(fl.exists()) {
									FileInputStream fis = new FileInputStream(fl);
									int readsize = 0;
									byte[] bytes = new byte[1024];
									dos.writeUTF(msg.substring(msg.indexOf(' ')));
									while((readsize = fis.read(bytes)) != -1) {
										dos.write(bytes, 0, readsize);
									}
									System.out.println();
									sandmsg = "[Start]\r\n"
											+ "Type::Upload\r\n"
											+ "[End]";
								} else {
									System.out.println("경로의 파일 없음.");
								}
							}else {
								sandmsg = "[Start]\r\n"
										+ "Type::Upload\r\n"
										+ "FilePath::" + msg +"\r\n"
										+ "FileName::" + msg +"\r\n"
										+ "[End]";
							}
							break;
						case "다운로드":
							sandmsg ="[Start]\r\n"
									+ "Type::DownLoad\r\n"
									+ "DownFilename::"+ msg.substring(0,msg.indexOf(' ')).trim() +"\r\n"
									+ "[End]";
							break;
						case "접속종료":
							sandmsg ="[Start]\r\n"
									+ "Type::Logout\r\n"
									+ "[End]";
							loginboolean = false;
							break;
					}
				} else {
					//알수 없는 메세지
//					sandmsg = "[Start]\r\n"
//							+ "Type::AllMessage\r\n"
//							+ "Message::\r\n"
//							+ msg + "\r\n"
//							+ "::Message\r\n"
//							+ "[End]";
				}
				pw.println(sandmsg);
			}
		} catch(Exception e) {
			System.out.println("접속 종료");
			e.printStackTrace();
		}
	}
}