package kr.hs.dgsw.Ex1;

public class Start {
	public static void main(String[] args) {
		People people1 = new People();
		People people2 = new People();

		people1.name = "±è¿µÈñ";
		people1.height = 165;
		people1.weight = 55;
		
		people2.name = "±èÃ¶¼ö";
		people2.height = 170;
		people2.weight = 90;
		
		people1.BMI();
		people2.BMI();
		
		people1.print();
		people2.print();
	}
	
}
