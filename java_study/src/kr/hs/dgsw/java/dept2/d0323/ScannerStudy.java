package kr.hs.dgsw.java.dept2.d0323;

import java.util.Scanner;

public class ScannerStudy {

	
	private Scanner scanner;
	
	public void prepareScanner() {
		this.scanner = new Scanner(System.in);
	}
	
	public void execute() {
		while(true) {
			System.out.println("문자열을 입력하세요.");
			String str = scanner.next();
			if("quit".equals(str)) {
				break;
			}
			System.out.println(str);	
		}
	}
	
	public void closeScanner() {
		this.scanner.close();
	}
	
	public static void main(String[] args) {
		ScannerStudy scannerStudy= new ScannerStudy();
		scannerStudy.prepareScanner();
		
		scannerStudy.execute();
		scannerStudy.closeScanner();
	}
}
