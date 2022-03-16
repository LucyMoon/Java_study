package kr.hs.dgsw.java.dept2;

public class Dog {
	String name;
	String color;
	int age;
	
	public void makeSound() {
		System.out.println("¸Û¸Û");
	}
	public void eat(String food) {
		System.out.printf("%sÀÌ(°¡) + %sÀ»(¸¦) ¸Ô´Â´Ù.\n", name, food);
	}
	public void printInformation() {
		System.out.printf("ÀÌ¸§ : %s\n", name);
		System.out.printf("»ö±ò : %s\n", color);
		System.out.printf("³ªÀÌ : %s\n", age);
	}
}
