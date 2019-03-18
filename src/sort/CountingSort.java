package sort;
import java.util.Arrays;

public class CountingSort {

	// Assumes that the input values are integers in a particular range
	public static void main(String[] args) {
		int[] dataArray = { 2, 5, 3, 0, 2, 3, 0, 3 };
		int arrayLength = findLength(dataArray);
		int[] countArray = new int[arrayLength];
		int[] sortedArray = new int[dataArray.length];
		// count number of repititions
		for (int i : dataArray) {
			countArray[i]++;
		}
		// add previous number to get number less than that number
		for (int k = 1; k < arrayLength; k++) {
			countArray[k] = countArray[k] + countArray[k - 1];
		}
		// start with last number and place it in correct position
		for (int j = dataArray.length - 1; j >= 0; j--) {
			sortedArray[countArray[dataArray[j]] - 1] = dataArray[j];
			countArray[dataArray[j]]--;
		}
		System.out.println(Arrays.toString(sortedArray));
	}

	public static int findLength(int[] dataArray) {
		int max = -65000;
		int min = 65000;
		for (int i : dataArray) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		return max - min + 1;
	}

}
