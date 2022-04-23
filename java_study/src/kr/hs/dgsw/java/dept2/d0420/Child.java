package kr.hs.dgsw.java.dept2.d0420;

public class Child extends Parent{
	protected int age;
	
	public static void main(String[] args) {
		Child  child = new Child();
		child.age = 18;
		child.name = "¿µÈñ";
		child.sayHello();
		
		Parent parent = new Parent();
		//parent.age = 45;
	}
}
