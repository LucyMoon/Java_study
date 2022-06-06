package kr.hs.dgsw.network.test01.n2208.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_Accept {
	public Socket sc = null;
	
	private OutputStream os = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private DataInputStream dis = null;
	
	
	// 생성
	public Client_Accept(Socket sc) {
		this.sc = sc;
		
		// 메시지 보내기 관련 객체 생성
		try {
			this.os = sc.getOutputStream();
			this.pw = new PrintWriter(this.os, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 메시지 받기 관련 객체 생성
		InputStream is = null;
		try {
			is = sc.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		// 클라이언트 메시지 입력 받음
		BufferedInputStream bir = new BufferedInputStream(is);
		this.dis = new DataInputStream(bir);
		this.br = new BufferedReader(new InputStreamReader(is));
	}
	
	// 메시지 보내기 부분
	public void sendMessage(String msg) {
		pw.println(msg);
	}
	
	
	// 메시지 받기 부분
	public String readMessage() throws IOException {
		return br.readLine();
	}
	
	public void UploadFile(String filename, String Size) {
		int TempSize = Integer.parseInt(Size);
		try {
			FileOutputStream fos = new FileOutputStream("D:\\temp\\" + filename.trim());
			
			int readsize = 0;
			byte[] bytes = new byte[1024 * 8];
			while((readsize = dis.read(bytes)) <= TempSize) {
				TempSize -= readsize;
				fos.write(bytes, 0, readsize);
//				System.out.println(TempSize);
				if(TempSize <= 0) {
					break;
				}
			}
			fos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
