package kr.hs.dgsw.java.dept2.d0427;

public class Dog extends Animal {

	@Override
	public String getName() {
		return "������";
	}
	
	@Override
	public String makeSound() {
		return "�۸�";
	}
	
	public void eat() {
		System.out.println("�ͱ��ͱ� �Խ��ϴ�.");
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog(); //eat ����
		Animal dog1 = new Dog(); //eat �Ұ���
		dog.move();
	}
	
}
