package kr.hs.dgsw.java.dept2.d0330;

import java.util.Scanner;

public class Calculator {
	private final String operator;

	private int operand1;
	
	private int operand2;
	
	private final Scanner scanner;
	
	public Calculator(String operator) {
		this.operator = operator;
		this.scanner = new Scanner(System.in);
	}
	
	public void execute() {
		while(true) {
			System.out.println("두 정수를 입력하세요.");
			this.operand1 = scanner.nextInt();
			this.operand2 = scanner.nextInt();
			if(operand1 == 0 && operand2 == 0) {
				break;
			}
			switch (operator) {
				case "*": {
					System.out.println(operand1*operand2);
					break;
				}
				case "/": {
					System.out.println(operand1/operand2);
					break;
				}
				case "+": {
					System.out.println(operand1+operand2);
					break;
				}
				case "-": {
					System.out.println(operand1-operand2);
				}
			}
		}
		this.scanner.close();
	}
	
	public static void main(String[] args) {
		String operator = args.length > 0 ? args[0] : "*";
		
		Calculator calculator = new Calculator(operator);
		calculator.execute();
	}
}
