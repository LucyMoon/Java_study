package kr.hs.dgsw.java.dept2;

public class Cat {
	String name;
	String color;
	int age;
	
	public void makeSound() {
		System.out.println("야옹");
	}
	public void eat(String food) {
		System.out.printf("%s이(가) + %s을(를) 먹는다.\n", name, food);
	}
	public void printInformation() {
		System.out.printf("이름 : %s\n", name);
		System.out.printf("색깔 : %s\n", color);
		System.out.printf("나이 : %s\n", age);
	}
}
