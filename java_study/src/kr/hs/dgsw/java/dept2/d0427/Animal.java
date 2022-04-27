package kr.hs.dgsw.java.dept2.d0427;

public abstract class Animal {
	
	public abstract String getName();
	
	public abstract String makeSound();
	
	public void move() {
		System.out.printf("%s(이)가 %s 소리를 내며 움직입니다.\n",
				getName(), makeSound());
	}
	
	public static void main(String[] args) {
		Animal animal = new Dog();
		animal.move();
		
	}
}
