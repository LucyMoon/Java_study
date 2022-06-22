package kr.hs.dgsw.java.dept2.d0622;

import java.util.Random;
import java.util.Scanner;

public class RandomGame {
	static private int win = 0;
	static private int lose = 0;
	static private int draw = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		RandomGame rg = new RandomGame();
		
		while(true) {
			System.out.println("���������� ��� �ϳ��� �Է��ϼ���.");
			System.out.printf("(�����) ");
			int userask = rg.ChangeStringtoInt(scan.nextLine());
			int computask = rg.Random();
			if(userask == 3) {
				break;
			} else if (userask == 4) {
				continue;
			}
			rg.RockPaperScissors(userask, computask);
		}
		System.out.printf("�� ���� : %d�� %d�� %d��", win, draw, lose);
		System.out.println("���α׷��� ����˴ϴ�.");
		
	}

	private int Random() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		return num;
	}
	
	private int ChangeStringtoInt(String ask) {
		switch (ask) {
		case "����": {
			return 0;
		}
		case "����": {
			return 1;
		}
		case "��": {
			return 2;
		}
		case "quit" : {
			return 3;
		}
		default:
			System.out.println("�˼����� ��ɾ �Է��ϼ̽��ϴ�.");
			return 4;
		}
	}
	
	private String ChangeInttoString(int ask) {
		switch (ask) {
		case 0: {
			return "����";
		}
		case 1: {
			return "����";
		}
		case 2: {
			return "��";
		}
		default:
			return "�˼�����";
		}
	}
	
	private void RockPaperScissors(int UserAsk, int ComputerAsk) {
		System.out.printf("%s vs %s : ", ChangeInttoString(UserAsk), ChangeInttoString(ComputerAsk));
		if((UserAsk == 0 && ComputerAsk == 2) || (UserAsk == 2 && ComputerAsk == 0)) {
			if(UserAsk > ComputerAsk) {
				System.out.println("�����ϴ�");
				lose ++;
			} else {
				System.out.println("�̰���ϴ�.");
				win ++;
			}
		}
		else if(UserAsk == ComputerAsk) {
			System.out.println("�����ϴ�");
			draw++;
		}
		else {
			if(UserAsk < ComputerAsk) {
				System.out.println("�����ϴ�");
				lose ++;
			} else {
				System.out.println("�̰���ϴ�.");
				win ++;
			}
		}
		System.out.println("���� : " + win + "�� " + draw + "�� " + lose + "��");
	}
}
