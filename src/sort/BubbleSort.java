package sort;

public class BubbleSort {

	public static void main(String[] args) {
		int[] dataArray = { 5, 2, 4, 6, 1, 3, 10, 67, 23, 95 };
		int temp = 0;
		// iterating all the elements
		for (int i = 0; i < dataArray.length; i++) {
			// replacing the next element if it is lower than
			// the current element
			for (int j = i + 1; j < dataArray.length; j++) {
				if (dataArray[j] < dataArray[i]) {
					temp = dataArray[i];
					dataArray[i] = dataArray[j];
					dataArray[j] = temp;
				}
			}
		}
		for (int i : dataArray) {
			System.out.println(i);
		}
	}

}
