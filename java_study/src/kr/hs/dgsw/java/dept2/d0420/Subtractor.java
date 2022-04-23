package kr.hs.dgsw.java.dept2.d0420;

public class Subtractor extends Adder{
	
	@Override
	public int calculate(Values val) {
		// TODO Auto-generated method stub
		return val.getValue1() - val.getValue2();
	}
	
	@Override
	public String getOperator() {
		// TODO Auto-generated method stub
		return "-";
	}
	
	public static void main(String[] args) {
		Subtractor subtractor = new Subtractor();
		subtractor.execute();
	}

}
