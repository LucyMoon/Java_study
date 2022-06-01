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
			System.out.println("���̵�� �н����带 �Է��ϼ���.");
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
			boolean loginboolean = true;
			while((loginboolean == true) && ((msg=scan.nextLine())!=null) ){
				if(!(msg == "")) {
					if(msg.substring(0,1).equals("/")){
						if(msg.indexOf(' ') != -1) {
							type = msg.substring(1, msg.indexOf(' '));
							msg = msg.substring(msg.indexOf(' '));
						} else {
							type = msg.substring(1);
						}
						switch(type) {
							case "���ϸ��":
								sandmsg = "[Start]\r\n"
										+"Type::FileList\r\n"
										+"[End]";
								break;
							case "���ε�":
								if(msg.trim().indexOf(' ') != -1) {
									path = msg.substring(0, msg.substring(1, msg.length()).indexOf(' ')+1).trim();
									fl = new File(path);
									if(fl.exists()) {
										filename = msg.substring(msg.substring(1, msg.length()).indexOf(' ')+2);
										et = msg.substring(msg.indexOf('.'), msg.substring(1, msg.length()).indexOf(' ')+1);
										sandmsg = "[Start]\r\n"
												+ "Type::Upload\r\n"
												+ "Size::" + fl.length() + "\r\n"
												+ "[End]";
										
									} else {
										System.out.println("����� ���� ����.");
									}
									break;
								}else {
									path = msg.trim();
									fl = new File(path);
									if(fl.exists()) {
										for(int i = path.length(); i > 0; i--) {
											if(path.substring(i-1, i).equals("/")) {
												filename = path.substring(i, path.length());
												i = 0;
											}
										}
										et = "";
										sandmsg = "[Start]\r\n"
												+ "Type::Upload\r\n"
												+ "Size::" + fl.length() + "\r\n"
												+ "[End]";
										
									} else {
										System.out.println("����� ���� ����.");
									}
									break;
								}
							case "�ٿ�ε�":
								sandmsg ="[Start]\r\n"
										+ "Type::DownLoad\r\n"
										+ "DownFilename::"+ msg.substring(msg.indexOf(' '), msg.length()).trim() +"\r\n"
										+ "[End]";
								break;
							case "��������":
								sandmsg ="[Start]\r\n"
										+ "Type::Logout\r\n"
										+ "[End]";
								loginboolean = false;
								break;
						}
					} else {
						//�˼� ���� �޼���
//						sandmsg = "[Start]\r\n"
//								+ "Type::AllMessage\r\n"
//								+ "Message::\r\n"
//								+ msg + "\r\n"
//								+ "::Message\r\n"
//								+ "[End]";
					}
					pw.println(sandmsg);
					if(type.equals("���ε�") && fl.exists()) {
						dos.writeUTF(filename+et);
						dos.flush();
					}
					
				}
			}
		} catch(Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
	}
	
	public void UploadFile() throws IOException {
		long tempsize = fl.length();
		System.out.println(fl.getAbsolutePath());
		FileInputStream fis = new FileInputStream(fl);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int readsize = 0;
		byte[] bytes = new byte[1024];
		while((readsize = fis.read(bytes)) <= tempsize) {
			tempsize -= readsize;
			if(tempsize == 0) {
				break;
			}
			dos.write(bytes, 0, readsize);
		}
		dos.flush();
		System.out.println("asd");
		
	}
	
	public void UploadOK() {
		String sandmsg = "[Start]\r\n"
				+ "Type::UploadOK\r\n"
				+ "[End]";
		pw.println(sandmsg);
	}
	
	public void Login() {
		checkLogin = true;
	}
}