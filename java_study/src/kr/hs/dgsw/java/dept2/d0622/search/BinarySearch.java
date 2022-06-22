package kr.hs.dgsw.java.dept2.d0622.search;

public class BinarySearch implements Searcher{

	@Override
	public int search(int[] sortedValues, int value) {
		int mid, low  = 0, high = sortedValues.length - 1;

		while(low <= high) {
			mid = (low + high) / 2;

			if(value == sortedValues[mid]) {
				return mid;
			} else if(value < sortedValues[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1; // Å½»ö ½ÇÆÐ 
	}

}
