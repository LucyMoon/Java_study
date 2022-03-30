package kr.hs.dgsw.java.dept2.d0330;

public class Dog {
	
	private final String name;
	private int age;
	
	public Dog(String name) {
		this(name, 0);
	}
	
	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("생성자가 호출되었습니다.");
	}
	
	public void print() {
		System.out.printf("%s 이름을 가진 강아지의 나이는 %d살입니다.\n", this.name, this.age);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		Dog happy = new Dog("해피");
		happy.print();
		Dog puppy = new Dog("퍼피");
		System.out.println("Count of args : " + args);
		for(int i = 0; i < args.length; i++) {
			System.out.printf("%d - %s\n", i, args[i]);
		}
		
		
	}

}
