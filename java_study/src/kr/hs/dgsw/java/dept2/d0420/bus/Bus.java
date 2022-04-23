package kr.hs.dgsw.java.dept2.d0420.bus;

public class Bus {
	
	protected int income;
	protected int countOfAdults;
	protected int countOfStudents;;
	
	public Bus() {
		this.income = 0;
		countOfAdults  = 0;
		countOfStudents = 0;
	}
	
	public void payment(Card card) {
		if(card.canPayment()) {
			income += card.requestPayment();
			
			if(card.getType().equals("�л�")) {
				this.countOfStudents++;
			} else {
				this.countOfAdults++;
			}
			System.out.println(card.getType() + "�Դϴ�. � ������.");
		} else {
			System.out.println("�ܾ��� �����մϴ�. ���� ��������.");
		}
	}
	
	public int getCountOfAdults() {
		
		return countOfAdults;
	}
	
	public int getCountOfStudents() {
		
		return countOfStudents;
	}
	
	public int getIncome() {
		
		return 0;
	}
	
	public static void main(String[] args) {
		Bus bus = new Bus();
		
		Card adult1 = new CardForAdult();
		adult1.charge(2000);
		
		Card adult2 = new CardForAdult();
		adult2.charge(5000);
		
		bus.payment(adult1);
		bus.payment(adult2);
		
		bus.payment(adult1);
		bus.payment(adult2);
		System.out.println("������ ���� : " + bus.getIncome());
		System.out.println("���� �մ� �� : " + bus.countOfAdults);
		System.out.println("�л� �մ� �� : " + bus.countOfStudents);
	}
}
