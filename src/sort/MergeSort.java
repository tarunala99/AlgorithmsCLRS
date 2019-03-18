package sort;

public class MergeSort {

	public static void main(String[] args) {
		int[] dataArray = { 5, 2, 4, 6, 1, 3, 10, 67, 23, 95 };

		// Select 3 values p, q, r that serve as the 3 extremes
		dataArray = mergeSort(dataArray, 0, dataArray.length);
		for (int i = 0; i < dataArray.length - 1; i++) {
			System.out.println(dataArray[i]);
		}
	}

	static int[] mergeSort(int[] dataArray, int p, int r) {
		if (dataArray.length == 2) {
			return dataArray;
		}
		// Always gives the floor of the value
		int midPoint = (p + r) / 2;
		int leftArrayLength = midPoint - p + 1;
		int rightArrayLength = r - midPoint + 1;
		int[] leftArray = new int[leftArrayLength];
		int[] rightArray = new int[rightArrayLength];
		System.arraycopy(dataArray, p, leftArray, 0, leftArrayLength - 1);
		System.arraycopy(dataArray, midPoint, rightArray, 0, rightArrayLength - 1);
		leftArray[leftArrayLength - 1] = 65000;
		rightArray[rightArrayLength - 1] = 65000;
		// construct array with new limits
		leftArray = mergeSort(leftArray, 0, leftArrayLength - 1);
		rightArray = mergeSort(rightArray, 0, rightArrayLength - 1);
		return merge(leftArray, rightArray);
	}

	static int[] merge(int[] leftArray, int[] rightArray) {
		int[] resultArray = new int[leftArray.length + rightArray.length - 1];
		int count = 0, count1 = 0, count2 = 0;
		while (count < resultArray.length) {
			if (leftArray[count1] < rightArray[count2]) {
				resultArray[count] = leftArray[count1];
				count1++;
			} else {
				resultArray[count] = rightArray[count2];
				count2++;
			}
			count++;
		}
		resultArray[resultArray.length - 1] = 65000;
		return resultArray;
	}

}
