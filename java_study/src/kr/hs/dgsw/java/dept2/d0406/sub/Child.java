package kr.hs.dgsw.java.dept2.d0406.sub;

import kr.hs.dgsw.java.dept2.d0406.AccessModifiler;

public class Child extends AccessModifiler {
	public static void main(String[] args) {
		Child child = new Child();
		child.publicValue = 5;
		child.privateValue = 3; //클래스가 달라서 사용 불가
		child.protectedValue = 4; 
		child.defaultValue = 1; //패키지가 달라서 사용 불가

	}
}
