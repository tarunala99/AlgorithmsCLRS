package sort;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

	public static void main(String[] args) {
		int[] dataArray = { 2, 8, 7, 1, 3, 5, 6, 4 };
		quickSort(dataArray, 0, dataArray.length - 1);
		System.out.println(Arrays.toString(dataArray));
	}

	public static void quickSort(int[] dataArray, int start, int end) {
		// just return if the start is no longer greater than end
		if (start >= end) {
			return;
		}
		int midPoint = partition(dataArray, start, end);
		quickSort(dataArray, start, midPoint - 1);
		quickSort(dataArray, midPoint + 1, end);
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
