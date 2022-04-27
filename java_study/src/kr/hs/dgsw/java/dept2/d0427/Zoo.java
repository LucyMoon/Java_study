package kr.hs.dgsw.java.dept2.d0427;

public class Zoo {
	public void breed(Animal animal) {
		animal.move();
	}
	
	public static void main(String[] args) {
		Zoo zoo = new Zoo();
		zoo.breed(new Dog());
		zoo.breed(new Pig());
		zoo.breed(new Horse());
	}
}
