package kr.hs.dgsw.java.dept2;

public class DogTester {
	public static void main(String[] args) {
		Dog jindo = new Dog();
		Dog chichu = new Dog();
		
		jindo.name = "ÈòµÕÀÌ";
		jindo.color = "³ë¶õ»ö";
		jindo.age = 3;
		
		chichu.name = "°ËµÕÀÌ";
		chichu.color = "ºÐÈ«»ö";
		chichu.age = 7;
		
		jindo.makeSound();
		jindo.eat("°³²­");
		jindo.printInformation();
		
		chichu.makeSound();
		chichu.eat("»ç·á");
		chichu.printInformation();
	}
}
