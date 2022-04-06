package kr.hs.dgsw.java.dept2.d0406;

import java.util.Scanner;

public class Decimal {
	
	private Scanner scanner;
	
	public int decimalfind(int num, int i) {
		if(num % i == 0) {
			return i;
		}
		return 0;
	}
	
	public void decimalprint(int num) {
		if(num != 0) {
			System.out.printf("%d ", num);
		}
	}
	
	public void prepareScanner() {
		this.scanner = new Scanner(System.in);
	}
	
	public int execute() {
		System.out.println("���ڸ� �Է��ϼ���.");
		int num = scanner.nextInt();
		return num;
	}
	
	public void closeScanner() {
		this.scanner.close();
	}

	public static void main(String[] args) {
		Decimal decimal = new Decimal();
		decimal.prepareScanner();
		
		int num = decimal.execute();
		
		System.out.printf("%d�� ����� ", num);
		for(int i = 1; i <= num; i++) {
			decimal.decimalprint(decimal.decimalfind(num, i));
		}
		System.out.printf("�Դϴ�.");
		decimal.closeScanner();
	}
}
