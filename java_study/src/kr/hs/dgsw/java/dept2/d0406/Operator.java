package kr.hs.dgsw.java.dept2.d0406;

public class Operator {
	public void studyArithemticOperators() {
		int value;
		
		value = 3 + 4;
		value = 5 - 3;
		value = 4 * 7;
		value = 6 / 3;
		value = 8 % 3;
		
		value += 5; // value = value + 5;
		value -= 3;
		value *= 2;
		value /= 6;
		value %= 3;
		
		value++;
		++value;
		value--;
		--value;
		
		for(int i = 0; i < 3; i++) {
			System.out.println(i);
		}
		value += (value + 3);
	}
	public void studyCompareOperators() {
		boolean result;
		result = 4 > 5;
		result = 5 == 5;
		result = 4!= 6;
		result = 3 >= 3;
		
		result = (4 > 2) && (5 > 7);
		result = (4 > 2) || (5 > 7);
		
		String str = null;
		if ((str != null) && (str.length() > 0)) {
			System.out.println("OK");
		}
	}
	
	public void studyBitwiseOperator() {
		int value1 = -1;
		int value2 =1;
		
		int value = value1 & value2;
		System.out.printf("%d & %d = %d\n", value1, value2, value);
		
		value = value1 | value2;
		System.out.printf("%d | %d = %d\n", value1, value2, value);

		value = value1 ^ value2;
		System.out.printf("%d ^ %d = %d\n", value1, value2, value);
		
		value1 = 1;
		value = value1 << 1;
		System.out.printf("%d << 1 = %d\n", value1, value);
		
		value1 = 1;
		value = value1 >> 1;
		System.out.printf("%d >> 1 = %d\n", value1, value);
		
		byte byteValue = -128;
		value = (byteValue >> 1);
		System.out.printf("%d >> 1 = %d\n", byteValue, value);
		
		byteValue = -128;
		value = (byteValue >>> 1);
		System.out.printf("%d >>> 1 = %d\n", byteValue, value);
		
	}
	
	public static void main(String[] args) {
		Operator operator = new Operator();
		operator.studyArithemticOperators();
		operator.studyBitwiseOperator();
	}
}
