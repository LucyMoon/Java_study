package kr.hs.dgsw.java.dept2.d0420;

public class Animal {
	public void move() {
		System.out.println("�����Դϴ�.");
	}
	
	public void eat(String food) {
		System.out.println(food + "�� �Խ��ϴ�.");
	}
	
	public void birth() {
		System.out.println("������ �����ϴ�.");
	}
	
	public static void main(String[] args) {
		Dog happy = new Dog();
		happy.birth();
		happy.bark();
		
		Animal puppy = new Dog();
		puppy.birth();
	}

}
