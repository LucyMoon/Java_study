package kr.hs.dgsw.java.dept2.d0406;

public class Other {

	public static void main(String[] args) {
		AccessModifiler accesModifiler = new AccessModifiler();
		accesModifiler.publicValue = 5;
		accesModifiler.privateValue = 3; //클래스가 달라서 사용 불가
		accesModifiler.protectedValue = 4;
		accesModifiler.defaultValue = 1;

	}
}
