package kr.hs.dgsw.java.dept2.d0427;

public class Dog extends Animal {

	@Override
	public String getName() {
		return "강아지";
	}
	
	@Override
	public String makeSound() {
		return "멍멍";
	}
	
	public void eat() {
		System.out.println("와구와구 먹습니다.");
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog(); //eat 가능
		Animal dog1 = new Dog(); //eat 불가능
		dog.move();
	}
	
}
