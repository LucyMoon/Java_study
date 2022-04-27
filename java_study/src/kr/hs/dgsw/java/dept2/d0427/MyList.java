package kr.hs.dgsw.java.dept2.d0427;

import java.util.ArrayList;
import java.util.List;

public class MyList extends ArrayList<String>{

	@Override
	public boolean add(String e) {
		// TODO Auto-generated method stub
		return super.add(e);
	}
	
	public static void main(String[] args) {
		List<String> list = new MyList();
		
		list.add("red");
		list.add("pink");
		list.add("blue");
		list.add("black");
		list.add("yellow");
		
		for(String color : list) {
			System.out.println(color);
		}
	}
}
