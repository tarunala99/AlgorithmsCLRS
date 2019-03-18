package data.structures;

import java.util.Arrays;

class Queue {

	// Implementing Queue with limited size
	// Else use ArrayList
	int[] queue = null;
	int front = -1;
	int end = -1;

	public Queue(int queueSize) {
		queue = new int[queueSize];
	}

	void enQueue(int data) {
		// If an element is present increment both the front
		// and the back
		if (front == -1) {
			front++;
		} else {
			// use the mod operation to parse back to 0
			if (front == (end + 1) % this.queue.length) {
				System.out.println("Queue is full");
				return;
			}
		}
		end = (end + 1) % this.queue.length;
		this.queue[this.end] = data;
	}

	int deQueue() {
		// If no value has been inserted in yet
		if (front == -1) {
			System.out.println("Queue is empty");
			return -200000;
		}
		// Check for empty value
		int removedData = this.queue[this.front];
		if (front == end) {
			front = -1;
			end = -1;
		} else {
			front = (front + 1) % this.queue.length;
		}
		return removedData;
	}

}

public class QueueOperations {
	public static void main(String args[]) {
		Queue newQueue = new Queue(10);
		for (int i = 0; i < 11; i++) {
			newQueue.enQueue(i);
		}
		System.out.println(Arrays.toString(newQueue.queue));
		for (int i = 9; i > -2; i--) {
			System.out.println(newQueue.deQueue());
		}
	}
}
