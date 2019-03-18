package sort;
class Data {

	Double value;
	Data next;
}

public class BucketSort {

	// Assumes that the input values are integers in a particular range
	public static void main(String[] args) {
		double[] dataArray = { 0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68 };
		double interval = findBuckets(dataArray);
		Data[] saveArray = new Data[dataArray.length];
		for (double data : dataArray) {
			double bucket = (data - 0.12) / interval;
			int index = (int) Math.floor(bucket);
			if (index == dataArray.length) {
				index--;
			}
			insertSort(saveArray, data, index);
		}
		traverseBuckets(saveArray);
	}

	public static void insertSort(Data[] saveArray, double data, int index) {
		Data node = saveArray[index];
		Data newValue = new Data();
		newValue.value = data;
		newValue.next = null;
		if (saveArray[index] == null) {
			saveArray[index] = newValue;
		} else {
			double temp;
			Data prev = node;
			while (node != null) {
				if (node.value > data) {
					temp = data;
					data = node.value;
					node.value = temp;
				}
				prev = node;
				node = node.next;
			}
			newValue.value = data;
			prev.next = newValue;
		}

	}

	public static double findBuckets(double[] dataArray) {
		double max = -65000;
		double min = 65000;
		for (double i : dataArray) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		return (max - min) / (dataArray.length);
	}

	public static void traverseBuckets(Data[] saveArray) {
		for (Data data : saveArray) {
			while (data != null) {
				System.out.println(data.value);
				data = data.next;
			}
		}
	}

}
