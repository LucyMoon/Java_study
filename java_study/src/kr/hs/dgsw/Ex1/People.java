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
			text = "��ü��";
		}
		else if(bmi < 25) {
			text = "ǥ��ü��";
		}
		else
			text = "��ü��";
		}
	
	public void print() {
		System.out.printf("%s�� �񸸵��� %.2f�̰� %s�Դϴ�.\n", name, bmi, text);
	}
}
