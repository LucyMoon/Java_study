package kr.hs.dgsw.java.dept2.d0323;

public class DataTypeStudy {
	public void studyInteger() {
		byte byteValue = -128;
		short shortValue = 1;
		int intValue = 2100000000;
		long longValue = 10000000000L;
	}
	
	public void studyReal() {
		float floatValue = 123453.14159261234567890F;
		double doubleValue = 123453.14159261234567890;
		
		System.out.println(floatValue);
		System.out.println(doubleValue);
	}
	public void studyBoolean() {
		
		boolean b1 = (4 > 2);
		boolean b2 = (3 == 2);
		
		System.out.println(b1);
		System.out.println(b2);
		
		if(false) {
			System.out.println("OK");
		}
	}
	
	public void studyChar() {
		char ch1 = 'a';
		char ch2 = 'a' + 1;
		int ch3 = 65;
		char ch4 = '¤¡';
		char ch5 = 'ô¸';
		
		System.out.println((int)ch1);
		System.out.println(ch2);
		System.out.println((char)ch3);
		System.out.println((int)ch4);
		System.out.println((int)ch5);
		System.out.println((char)(ch4+1));
		System.out.println((int)'ÆR' - (int)'°¡' + 1);
		
		for(char ch = '°¡'; ch < '°í'; ch++) {
			System.out.printf("%d - %c\n", (int)ch, ch);
		}
	}
	
	public void studyAscii() {
		for(int i = 0; i < 256; i++) {
			System.out.printf("%d - %c\n", i, (char)i);
		}
	}
	
	public static void main(String[] args) {
		DataTypeStudy study = new DataTypeStudy();
		study.studyChar();
	}
}
