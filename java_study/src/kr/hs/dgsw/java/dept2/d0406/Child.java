package kr.hs.dgsw.java.dept2.d0406;

public class Child extends AccessModifiler {

	public static void main(String[] args) {
		Child child = new Child();
		child.publicValue = 4;
		child.privateValue = 5; //클래스가 달라서 사용 불가
		child.protectedValue = 3;
		child.defaultValue = 2;

	}
}
