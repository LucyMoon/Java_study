package kr.hs.dgsw.java.dept2.d0406.sub;

import kr.hs.dgsw.java.dept2.d0406.AccessModifiler;

public class Other {

	public static void main(String[] args) {
		AccessModifiler accesModifiler = new AccessModifiler();
		accesModifiler.publicValue = 5;
		accesModifiler.privateValue = 3; //Ŭ������ �޶� ��� �Ұ�
		accesModifiler.protectedValue = 4; //Ŭ������ extends AccessModifiler ������ ���� �ʾƼ� ��� �Ұ�
		accesModifiler.defaultValue = 1; //��Ű���� �޶� ��� �Ұ�

	}
}
