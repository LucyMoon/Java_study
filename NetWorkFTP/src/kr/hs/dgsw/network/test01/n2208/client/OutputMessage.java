package kr.hs.dgsw.network.test01.n2208.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

public class OutputMessage extends Thread {
	private PrintWriter pw = null;
	private Socket sc = null;
	private boolean checkLogin = false;
	private String type = "";
	private File fl = null;
	private DataOutputStream dos = null;
	private String et = "";
	private String filename = "";
	
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
		dos = new DataOutputStream(bor);
		
		pw = new PrintWriter(os, true);
		
		while(!checkLogin) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(checkLogin) {
				break;				
			}
			System.out.println("아이디와 패스워드를 입력하세요.");
			String Id = scan.nextLine();
			String Pw = scan.nextLine();
			String sandmsg ="[Start]\r\n"
					+ "Type::Login\r\n"
					+ "ID::"+ Id +"\r\n"
					+ "PW::"+ Pw +"\r\n"
					+ "[End]";
			pw.println(sandmsg);
		}
		
		try {
			String msg = "";
			String sandmsg ="";
			String path = "";
			type = "";
			boolean loginboolean = true;
			while(loginboolean == true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				msg=scan.nextLine();
				if(!(msg == "")) {
					if(msg.substring(0,1).equals("/")){
						if(msg.indexOf(' ') != -1) {
							type = msg.substring(1, msg.indexOf(' '));
							msg = msg.substring(msg.indexOf(' '));
						} else {
							type = msg.substring(1);
						}
						switch(type) {
							case "파일목록":
								System.out.println("--파일목록--");
								sandmsg = "[Start]\r\n"
										+"Type::FileList\r\n"
										+"[End]";
								break;
							case "업로드":
								if(msg.trim().indexOf(' ') != -1) {
									path = msg.substring(0, msg.substring(1, msg.length()).indexOf(' ')+1).trim();
									fl = new File(path);
									if(fl.exists()) {
										filename = msg.substring(msg.substring(1, msg.length()).indexOf(' ')+2);
										et = msg.substring(msg.indexOf('.'), msg.substring(1, msg.length()).indexOf(' ')+1);
										sandmsg = "[Start]\r\n"
												+ "Type::Upload\r\n"
												+ "SendFilePath::" + fl.getAbsolutePath() +"\r\n"
												+ "FN::" + filename + "\r\n"
												+ "ET::" + et + "\r\n"
												+ "[End]";
										
									} else {
										System.out.println("경로의 파일 없음.");
									}
									break;
								}else {
									path=msg.trim();

									fl = new File(path);
									int tempint = 3;
									if(fl.exists()) {
										for(int i = path.length(); i > 0; i--) {
											if(path.substring(i-1, i).equals("\\")) {
												tempint = i+1;
												i = 0;
											}
										}
										filename = msg.substring(tempint, msg.indexOf('.'));

										et = msg.substring(msg.indexOf('.'), msg.length());
										sandmsg = "[Start]\r\n"
												+ "Type::Upload\r\n"
												+ "SendFilePath::" + fl.getAbsolutePath() +"\r\n"
												+ "FN::" + filename + "\r\n"
												+ "ET::" + et + "\r\n"
												+ "[End]";
										
									} else {
										System.out.println("경로의 파일 없음.");
									}
									break;
								}
							case "다운로드":
								sandmsg ="[Start]\r\n"
										+ "Type::DownLoad\r\n"
										+ "DownFilename::"+ msg.substring(msg.indexOf(' '), msg.length()).trim() +"\r\n"
										+ "[End]";
								break;
							case "접속종료":
								sandmsg ="[Start]\r\n"
										+ "Type::Logout\r\n"
										+ "[End]";
								loginboolean = false;
								break;
								
								
						}
						pw.println(sandmsg);
					} else {
						System.out.println("알수없는 메세지..");
					}
					
					
				}
			}
		} catch(Exception e) {
			System.out.println("접속 종료");
			e.printStackTrace();
		}
	}
	
	public void UploadFile(String CheckFilePath) throws IOException {
		File FP = new File(CheckFilePath);
		BigInteger tempsize = BigInteger.valueOf(FP.length());

		FileInputStream fis = new FileInputStream(FP);
		int readsize = 0;
		byte[] bytes = new byte[1024 * 64];
		while(true) {
			readsize = fis.read(bytes);
			tempsize = tempsize.subtract(BigInteger.valueOf(readsize));
			dos.write(bytes, 0, readsize);
			if(tempsize.compareTo(BigInteger.ZERO) == 0) {
				break;
			}
		}
		dos.flush();
		
	}
	
	public void UploadOK(String CheckFileName) {
		String sandmsg = "[Start]\r\n"
				+ "Type::UploadOK\r\n"
				+ "Size::" + fl.length() + "\r\n"
				+ "Filename::" + CheckFileName+et + "\r\n"
				+ "[End]";
		pw.println(sandmsg);
	}
	
	public void Login() {
		checkLogin = true;
	}
}