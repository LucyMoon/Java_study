package kr.hs.dgsw.java.dept2.d0420.bus;

public class CardForAdult extends Card{

	@Override
	public int requestPayment() {
		if(this.canPayment()) {
			this.balabce -= 1200;
			return 1200;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public boolean canPayment() {
		// TODO Auto-generated method stub
		return this.balabce >= 1200;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "º∫¿Œ";
	}
}
