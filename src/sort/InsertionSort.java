package sort;

public class InsertionSort {

	public static void main(String[] args) {
		int[] dataArray = { 5, 2, 4, 6, 1, 3, 10, 67, 23, 95 };
		int temp = 0;
		// iterating from the second element
		for (int i = 1; i < dataArray.length; i++) {
			// finding the element lower than the current
			// number and replacing the ones after that
			for (int j = 0; j < i; j++) {
				if (dataArray[j] > dataArray[i]) {
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
