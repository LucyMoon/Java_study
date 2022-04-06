package kr.hs.dgsw.java.dept2.d0406;

public class AccessModifiler {
	public int publicValue;
	
	private int privateValue;
	
	protected int protectedValue;
	
	int defaultValue;
	
	public static void main(String[] args) {
		AccessModifiler accesModifiler = new AccessModifiler();
		accesModifiler.publicValue = 5;
		accesModifiler.privateValue = 3;
		accesModifiler.protectedValue = 4;
		accesModifiler.defaultValue = 1;
		
	}
}
