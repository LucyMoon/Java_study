package kr.hs.dgsw.java.dept2;

public class CatTester {
	public static void main(String[] args) {
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		
		cat1.name = "�̹�";
		cat1.color = "�����";
		cat1.age = 3;
		
		cat2.name = "�ǻ�";
		cat2.color = "��ȫ��";
		cat2.age = 7;
		
		cat1.makeSound();
		cat1.eat("����");
		cat1.printInformation();
		
		cat2.makeSound();
		cat2.eat("���");
		cat2.printInformation();
	}
}