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
		
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

	private boolean questions() {
		String ask;
		System.out.printf("���ڸ� �Է����ּ���(\n0 : ����\n1:�̸��� ��ȭ��ȣ ����ϱ�\n2:�̸����� ��ȭ��ȣ �˻��ϱ�\n3:��ȭ��ȣ�� �Ϻη� ��ȭ��ȣ �˻��ϱ�\n4:�̸����� ��ȭ��ȣ �����ϱ�) : ");
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
			System.out.println("�˼����� ��ɾ�");
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
			System.out.println(temp+"�� �����Ǿ����ϴ�.");
			FileWriter fw = new FileWriter(fl);
			fw.write(list);
			fw.flush();
			fr.close();
			br.close();
			fw.close();
			System.out.println("���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void DeleteasName() {
		String temp;
		ArrayList<String> list = CommonPrint("������ �̸��� �Է��ϼ���. : ", 0);
		if(list.size() <= 0) {
			System.out.println("�ش�Ǵ� ����� �����ϴ�.");
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
			System.out.printf("����߿��� ��Ȯ�� ��ȭ��ȣ�� �Է����ּ���. : ");
			ask = scan.nextLine();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).substring(list.get(i).indexOf('-')+1).equals(ask)) {
					return list.get(i);
				}
			}
			System.out.println("��ġ�ϴ� ��ȭ��ȣ�� �����ϴ�.");
		}
	}
	
	void SearchasPhonenum() {
		CommonPrint("�˻��� ��ȭ��ȣ�� �Ϻκ��� �Է��ϼ���. : ", 1);
	}

	void SearchasName() {
		CommonPrint("�˻��� ���ڶǴ� ���ڿ��� �Է��ϼ���. : ", 0);
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
		System.out.printf("�̸��� �Է����ּ���. : ");
		Name = scan.nextLine();
		while (true) {
			System.out.printf("��ȭ��ȣ�� �Է����ּ��� : ");
			Phonenum = scan.nextLine();
			if (Phonenum.length() != 11) {
				System.out.println("��ȭ��ȣ�� 11�ڸ����� �մϴ�.");
			} else if (!isNumeric(Phonenum)) {
				System.out.println("��ȭ��ȣ�� ���ڰ� �ƴ� ���� �����ϴ�.");
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
		System.out.println("��� ����");
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