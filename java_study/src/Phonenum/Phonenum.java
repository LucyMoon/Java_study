package Phonenum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Phonenum {
	private String FilePath = "D:/SavePhonenum.txt";
	private File fl = new File(FilePath);
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Phonenum pn = new Phonenum();
		while (pn.questions()) {}
		
		System.out.println("프로그램이 종료되었습니다.");
	}

	private boolean questions() {
		String ask;
		System.out.printf("숫자를 입력해주세요(\n0 : 종료\n1:이름과 전화번호 등록하기\n2:이름으로 전화번호 검색하기\n3:전화번호의 일부로 전화번호 검색하기\n4:이름으로 전화번호 삭제하기) : ");
		ask = scan.nextLine();
		switch (ask) {
		case "0": {
			scan.close();
			return false;
		}
		case "1": {
			InputPhonenum();
			break;
		}
		case "2": {
			SearchasName();
			break;
		}
		case "3": {
			SearchasPhonenum();
			break;
		}
		case "4": {
			DeleteasName();
			break;
		}
		default:
			System.out.println("알수없는 명령어");
			break;
		}
		return true;
	}
	
	void Delete(String temp) {
		String list = "";
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				if(!(line.equals(temp))){
					list = list + line + "\r\n";
				}
			}
			System.out.println(temp+"가 삭제되었습니다.");
			FileWriter fw = new FileWriter(fl);
			fw.write(list);
			fw.flush();
			fr.close();
			br.close();
			fw.close();
			System.out.println("삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void DeleteasName() {
		String temp;
		ArrayList<String> list = CommonPrint("삭제할 이름을 입력하세요. : ", 0);
		if(list.size() <= 0) {
			System.out.println("해당되는 목록이 없습니다.");
		}
		else if(list.size() == 1) {
			Delete(list.get(0));
		} else {
			temp = DeleteasPhonenum(list);
			Delete(temp);
		}
	}
	
	String DeleteasPhonenum(ArrayList<String> list) {
		String ask;
		String temp;
		while(true) {
			System.out.printf("목록중에서 정확한 전화번호를 입력해주세요. : ");
			ask = scan.nextLine();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).substring(list.get(i).indexOf('-')+1).equals(ask)) {
					return list.get(i);
				}
			}
			System.out.println("일치하는 전화번호가 없습니다.");
		}
	}
	
	void SearchasPhonenum() {
		CommonPrint("검색할 전화번호의 일부분을 입력하세요. : ", 1);
	}

	void SearchasName() {
		CommonPrint("검색할 문자또는 문자열을 입력하세요. : ", 0);
	}

	private boolean isNumeric(String num) {
		char temp;
		for (int i = 0; i < num.length(); i++) {
			temp = num.charAt(i);
			if (Character.isDigit(temp) == false) {
				return false;
			}
		}
		return true;
	}

	private void InputPhonenum() {
		String Name = "";
		String Phonenum = "";
		System.out.printf("이름을 입력해주세요. : ");
		Name = scan.nextLine();
		while (true) {
			System.out.printf("전화번호를 입력해주세요 : ");
			Phonenum = scan.nextLine();
			if (Phonenum.length() != 11) {
				System.out.println("전화번호는 11자리여야 합니다.");
			} else if (!isNumeric(Phonenum)) {
				System.out.println("전화번호에 숫자가 아닌 값이 들어갔습니다.");
			} else {
				break;
			}
		}
		SavePhonenum(Name, Phonenum);
	}

	private void SavePhonenum(String Name, String Phonenum) {
		try {
			FileWriter fw = new FileWriter(fl, true);
			fw.write(Name + "-" + Phonenum + "\r\n");

			fw.flush();

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("등록 성공");
	}
	
	private ArrayList<String> CommonPrint(String StringPrint, int index) {
		String ask;
		ArrayList<String> listPhonenum = new ArrayList<String>();
		System.out.printf(StringPrint);
		ask = scan.nextLine();
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split("-");
				if (temp[index].contains(ask)) {
					listPhonenum.add(line);
				}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		listPhonenum.sort(null);
		for(int i = 0; i < listPhonenum.size(); i++) {
			System.out.println(listPhonenum.get(i));
		}
		return listPhonenum;
	}
}