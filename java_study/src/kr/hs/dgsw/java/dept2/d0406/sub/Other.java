package kr.hs.dgsw.java.dept2.d0406.sub;

import kr.hs.dgsw.java.dept2.d0406.AccessModifiler;

public class Other {

	public static void main(String[] args) {
		AccessModifiler accesModifiler = new AccessModifiler();
		accesModifiler.publicValue = 5;
		accesModifiler.privateValue = 3; //클래스가 달라서 사용 불가
		accesModifiler.protectedValue = 4; //클래스에 extends AccessModifiler 참조를 하지 않아서 사용 불가
		accesModifiler.defaultValue = 1; //패키지가 달라서 사용 불가

	}
}
