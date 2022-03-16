package kr.hs.dgsw.java.dept2;

public class CatTester {
	public static void main(String[] args) {
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		
		cat1.name = "¹Ì¹Ì";
		cat1.color = "³ë¶õ»ö";
		cat1.age = 3;
		
		cat2.name = "»Ç»ß";
		cat2.color = "ºÐÈ«»ö";
		cat2.age = 7;
		
		cat1.makeSound();
		cat1.eat("°£½Ä");
		cat1.printInformation();
		
		cat2.makeSound();
		cat2.eat("»ç·á");
		cat2.printInformation();
	}
}