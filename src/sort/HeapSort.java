package sort;
import java.util.Arrays;

public class HeapSort {

	public static int heapLength = 0;

	public static void main(String[] args) {
		int[] dataArray = { 16, 4, 10, 14, 7, 9, 3, 2, 8, 1 };
		maxHeapify(dataArray, 2);
		System.out.println(Arrays.toString(dataArray));
		int[] dataArraySample = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		heapLength = dataArraySample.length;
		buildMaxHeap(dataArraySample);
		System.out.println(Arrays.toString(dataArraySample));
		heapSort(dataArraySample);
	}

	// All positions assuming they start from 1
	public static int leftChild(int index) {
		return 2 * index;
	}

	public static int rightChild(int index) {
		return 2 * index + 1;
	}

	public static int parent(int index) {
		return Math.floorDiv(index, 2);
	}

	// The array under the index is assumed to be always sorted
	public static void maxHeapify(int[] dataArray, int index) {
		int largest = index - 1;
		int left = leftChild(index) - 1;
		int right = rightChild(index) - 1;
		// Check if the child exists as well
		if (left < heapLength && dataArray[left] > dataArray[largest]) {
			largest = left;
		}
		if (right < heapLength && dataArray[right] > dataArray[largest]) {
			largest = right;
		}

		if (largest == index - 1)
			return;
		else {
			// exchange the largest with the index
			int temp = dataArray[index - 1];
			dataArray[index - 1] = dataArray[largest];
			dataArray[largest] = temp;
			maxHeapify(dataArray, largest + 1);
		}
	}

	// Start with the child which has its first parent that is length/2
	public static void buildMaxHeap(int[] dataArray) {
		int parentNode = parent(dataArray.length);
		while (parentNode > 0) {
			maxHeapify(dataArray, parentNode);
			parentNode--;
		}
	}

	// Exchange element with the last element and perform max heapify
	// Keep decreasing the size of the array
	public static void heapSort(int[] dataArray) {
		while (heapLength > 1) {
			int temp = dataArray[heapLength - 1];
			dataArray[heapLength - 1] = dataArray[0];
			dataArray[0] = temp;
			heapLength--;
			maxHeapify(dataArray, 1);
		}
		System.out.println(Arrays.toString(dataArray));
	}
}
