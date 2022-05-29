package kr.hs.dgsw.network.test01.n2208.client;

import java.net.Socket;

public class ChattingClientMessage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Socket sc = new Socket("127.0.0.1",5000);
			
			OutputMessage om = new OutputMessage(sc);
			om.start();
			
			InputMessage im = new InputMessage(sc);
			im.start();
		} catch(Exception e) {
			System.out.println("연결 종료");
			e.printStackTrace();
		}
	}

}
