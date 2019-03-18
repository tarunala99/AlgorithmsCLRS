package data.structures;

import java.util.Arrays;

class Stack {

	// Implementing stack with limited size
	// Else use ArrayList
	int[] stack = null;
	int top = -1;

	public Stack(int stackSize) {
		stack = new int[stackSize];
	}

	void push(int data) {
		if (top >= stack.length - 1) {
			System.out.println("stack overflow");
			return;
		}
		top++;
		this.stack[this.top] = data;
	}

	int pop() {
		if (top == -1) {
			System.out.println("stack underflow");
			return -20000;
		}
		int poppedValue = this.stack[this.top];
		top--;
		return poppedValue;
	}

	boolean stackEmpty() {
		if (this.top == -1) {
			return true;
		}
		return false;
	}

}

public class StackOperations {
	public static void main(String args[]) {
		Stack newStack = new Stack(10);
		for (int i = 0; i < 11; i++) {
			newStack.push(i);
		}
		System.out.println(Arrays.toString(newStack.stack));
		for (int i = 9; i > -2; i--) {
			System.out.println(newStack.pop());
		}
	}
}
