package kr.hs.dgsw.java.dept2.d0406.sub;

import kr.hs.dgsw.java.dept2.d0406.AccessModifiler;

public class Child extends AccessModifiler {
	public static void main(String[] args) {
		Child child = new Child();
		child.publicValue = 5;
		child.privateValue = 3; //Ŭ������ �޶� ��� �Ұ�
		child.protectedValue = 4; 
		child.defaultValue = 1; //��Ű���� �޶� ��� �Ұ�

	}
}
