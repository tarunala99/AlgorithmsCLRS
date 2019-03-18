package sort;

import java.util.concurrent.ThreadLocalRandom;

public class IthElement {

	public static void main(String[] args) {
		int[] dataArray = { 2, 8, 7, 1, 3, 5, 6, 4, 9 };
		int median = linearSearch(dataArray, 0, dataArray.length - 1, 6);
		System.out.println("the value is" + median);
	}

	public static int linearSearch(int[] dataArray, int start, int end, int median) {
		// just return if the start is no longer greater than end
		if (start == end) {
			return start;
		}

		int midPoint = partition(dataArray, start, end);
		int k = midPoint - start + 1; // Get where the midpoint index is going to be
		// located at
		if (k == median) {
			return dataArray[midPoint];
		} else {
			if (median < k) {
				return linearSearch(dataArray, start, midPoint - 1, median);
			} else {
				return linearSearch(dataArray, midPoint + 1, end, median - k);
			}
		}
	}

	public static int partition(int[] dataArray, int start, int end) {
		// randomizing the input to get the best case performance
		int randomNum = ThreadLocalRandom.current().nextInt(start, end + 1);
		int temp = 0;
		temp = dataArray[randomNum];
		dataArray[randomNum] = dataArray[end];
		dataArray[end] = temp;
		// incase there is no element that is smaller
		int midPoint = start - 1;
		for (int i = start; i <= end - 1; i++) {
			if (dataArray[i] < dataArray[end]) {
				midPoint++;
				if (midPoint != i) {
					temp = dataArray[midPoint];
					dataArray[midPoint] = dataArray[i];
					dataArray[i] = temp;
				}
			}
		}
		// switch the last element in
		temp = dataArray[midPoint + 1];
		dataArray[midPoint + 1] = dataArray[end];
		dataArray[end] = temp;
		return midPoint + 1;
	}
}
