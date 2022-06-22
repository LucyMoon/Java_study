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
			System.out.println("가위바위보 가운데 하나를 입력하세요.");
			System.out.printf("(사용자) ");
			int userask = rg.ChangeStringtoInt(scan.nextLine());
			int computask = rg.Random();
			if(userask == 3) {
				break;
			} else if (userask == 4) {
				continue;
			}
			rg.RockPaperScissors(userask, computask);
		}
		System.out.printf("총 전적 : %d승 %d무 %d패", win, draw, lose);
		System.out.println("프로그램이 종료됩니다.");
		
	}

	private int Random() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		return num;
	}
	
	private int ChangeStringtoInt(String ask) {
		switch (ask) {
		case "가위": {
			return 0;
		}
		case "바위": {
			return 1;
		}
		case "보": {
			return 2;
		}
		case "quit" : {
			return 3;
		}
		default:
			System.out.println("알수없는 명령어를 입력하셨습니다.");
			return 4;
		}
	}
	
	private String ChangeInttoString(int ask) {
		switch (ask) {
		case 0: {
			return "가위";
		}
		case 1: {
			return "바위";
		}
		case 2: {
			return "보";
		}
		default:
			return "알수없음";
		}
	}
	
	private void RockPaperScissors(int UserAsk, int ComputerAsk) {
		System.out.printf("%s vs %s : ", ChangeInttoString(UserAsk), ChangeInttoString(ComputerAsk));
		if((UserAsk == 0 && ComputerAsk == 2) || (UserAsk == 2 && ComputerAsk == 0)) {
			if(UserAsk > ComputerAsk) {
				System.out.println("졌습니다");
				lose ++;
			} else {
				System.out.println("이겼습니다.");
				win ++;
			}
		}
		else if(UserAsk == ComputerAsk) {
			System.out.println("비겼습니다");
			draw++;
		}
		else {
			if(UserAsk < ComputerAsk) {
				System.out.println("졌습니다");
				lose ++;
			} else {
				System.out.println("이겼습니다.");
				win ++;
			}
		}
		System.out.println("전적 : " + win + "승 " + draw + "무 " + lose + "패");
	}
}
