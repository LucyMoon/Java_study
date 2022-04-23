package kr.hs.dgsw.java.dept2.d0420.bus;

public class Card {
	protected int balabce;
	
	public Card() {
		this.balabce = 0;
	}
	
	public void charge(int amount) {
		this.balabce += amount;
	}
	
	public int requestPayment() {
		return 0;
	}
	
	public boolean canPayment() {
		return false;
	}
	
	public String getType() {
		return null;
	}
}
