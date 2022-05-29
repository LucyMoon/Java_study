package kr.hs.dgsw.network.test01.n2208.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
	
	// ����
	public Client_Accept(Socket sc) {
		this.sc = sc;
		
		// �޽��� ������ ���� ��ü ����
		try {
			this.os = sc.getOutputStream();
			this.pw = new PrintWriter(this.os, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// �޽��� �ޱ� ���� ��ü ����
		InputStream is = null;
		try {
			is = sc.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		// Ŭ���̾�Ʈ �޽��� �Է� ����
		BufferedInputStream bir = new BufferedInputStream(is);
		this.dis = new DataInputStream(bir);
		this.br = new BufferedReader(new InputStreamReader(is));
	}
	
	// �޽��� ������ �κ�
	public void sendMessage(String msg) {
		pw.println(msg);
	}
	
	
	// �޽��� �ޱ� �κ�
	public String readMessage() throws IOException {
		return br.readLine();
	}
	
	public String readFile() throws IOException {
		String filename = dis.readUTF();
		
		FileOutputStream fos = new FileOutputStream("D:\\temp\\" + filename);
		
		int readsize = 0;
		byte[] bytes = new byte[1024];
		
		while((readsize = dis.read(bytes)) != -1) {
			fos.write(bytes, 0, readsize);
		}
		return filename;
	}
}
