package kr.hs.dgsw.java.dept2.d0330;

public class Cat {
	private final String name;
	private String color;
	private int weight;
	
	
	public Cat(String name, String color, int weight) {
		this.name = name;
		this.color = color;
		this.weight = weight;
	}
	
	public void print() {
		System.out.printf("%s 이름을 가진 고양이의 색깔은 %s이고, 몸무게는 %d입니다.\n", name, color, weight);
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}
	
	public static void main(String[] args) {
		Cat happy = new Cat("해피", "검정", 5);
		happy.print();
		Cat cheese = new Cat("치즈", "노랑", 7);
		cheese.print();
	}
}
