package kr.hs.dgsw.Ex1;

public class People {
	String name;
	int height;
	int weight;
	
	double bmi;
	String text;
	
	public void BMI() {
		bmi = weight / (((double)height/100) * ((double)height/100));
		if(bmi < 20) {
			text = "저체중";
		}
		else if(bmi < 25) {
			text = "표준체중";
		}
		else
			text = "과체중";
		}
	
	public void print() {
		System.out.printf("%s의 비만도는 %.2f이고 %s입니다.\n", name, bmi, text);
	}
}
