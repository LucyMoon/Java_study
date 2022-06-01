package kr.hs.dgsw.network.test01.n2208.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ChattingServerMessage {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(5000);  	// ���� ����(Ŭ���̾�Ʈ ���� ���)

			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while(true) {
				Socket sc = ss.accept();
				System.out.println(sc.getInetAddress() +": �����Ͽ����ϴ�.");
				
				try {
					// ������ �ְ� �޴� ��ü ����
					Client_Accept ca = new Client_Accept(sc);
					
					Thread ct = new ClientThread(sc.getInetAddress().toString(), ca);
					ct.start();
				} catch(Exception e) {
					System.out.println("���� ����");
				}
			}
		} catch(Exception e) {
		} finally {
			try {
				ss.close();
			} catch(IOException e) {}
		}
	}
}