package kr.hs.dgsw.java.dept2.d0427;

import java.util.ArrayList;
import java.util.List;

public class listStudy {
	public void studyList() {
		List<String> list = new ArrayList<String>();
		String[] array = new String[5];
		
		//���� �����ϱ�
		array[0] = "blue";
		array[1] = "black";
		
		list.add("blue");
		list.add("black");
		list.add("yello");
		list.add("red");
		list.add("pink");
		
		list.add(2, "white"); // ���� �������� ��
		
		//���� �о����
		String str = array[0];
		
		str = list.get(0);
		str = list.get(1);
		
		//ũ��
		int size = array.length;
		
		size = list.size();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + " - " + list.get(i));
		}
		
		for(String item : list) {
			System.out.println(item);
		}
		
		//����
		String str1 = list.remove(0);
	}
}