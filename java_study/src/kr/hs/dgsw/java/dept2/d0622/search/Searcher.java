package kr.hs.dgsw.java.dept2.d0622.search;

public interface Searcher {

	/**
	 * �־��� ���ĵ� �迭���� �־��� ���� �����ϴ� ��ġ�� �ε����� �ǵ�����.
	 * 
	 * @param sortedValues ������������ ���ĵ� ���� �迭
	 * @param value ã�� ��
	 * @return value�� ��ġ�ϴ� �ε���, ���� �迭�� �־��� ���� ������ -1
	 */
	public int search(int[] sortedValues, int value);
}