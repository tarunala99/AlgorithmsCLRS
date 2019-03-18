package search.breadth;

enum Colour {
	WHITE, GREY, BLACK;
}

class Queue {

	// Implementing Queue with limited size
	// Else use ArrayList
	Vertex[] queue = null;
	int front = -1;
	int end = -1;

	public Queue(int queueSize) {
		queue = new Vertex[queueSize];
	}

	void enQueue(Vertex data) {
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

	Vertex deQueue() {
		// If no value has been inserted in yet
		if (front == -1) {
			System.out.println("Queue is empty");
			return null;
		}
		// Check for empty value
		Vertex removedData = this.queue[this.front];
		if (front == end) {
			front = -1;
			end = -1;
		} else {
			front = (front + 1) % this.queue.length;
		}
		return removedData;
	}

	boolean isEmpty() {
		// If no value has been inserted in yet
		if (front == -1) {
			return true;
		}
		return false;
	}

}

class Vertex {
	String data = null;

	Vertex parent = null;

	int distance = (int) Math.pow(2, 32);

	Colour colour = Colour.WHITE;

	Edge edge = null;

	public Vertex(String data) {
		super();
		this.data = data;
	}
}

class Edge {
	int weight = 1;

	Edge next = null;

	Vertex dest = null;
}

public class BreadthFirstSearch {

	public static void main(String[] args) {
		Vertex[] inputGraph = createGraph();
		Queue vertexQueue = new Queue(inputGraph.length);
		calculateDistances(inputGraph, vertexQueue);
		travelledPath(inputGraph, 7);

	}

	public static void travelledPath(Vertex[] inputGraph, int node) {
		Vertex parent = inputGraph[node];
		System.out.print(parent.data);
		while (parent.parent != null) {
			System.out.print(" -> ");
			parent = parent.parent;
			System.out.print(parent.data);
		}
		System.out.println("\n The total distance is " + inputGraph[node].distance);
	}

	public static void calculateDistances(Vertex[] inputGraph, Queue vertexQueue) {
		inputGraph[0].distance = 0;
		inputGraph[0].colour = Colour.GREY;
		vertexQueue.enQueue(inputGraph[0]);
		while (!vertexQueue.isEmpty()) {
			Vertex node = vertexQueue.deQueue();
			Edge tempEdge = node.edge;
			Vertex tempNode = null;
			int temp = 0;
			while (tempEdge != null) {
				if (tempEdge.dest.colour != Colour.GREY && tempEdge.dest.colour != Colour.BLACK) {
					tempNode = tempEdge.dest;
					tempNode.colour = Colour.GREY;
					temp = node.distance + tempEdge.weight;
					if (temp < tempNode.distance) {
						tempNode.distance = temp;
						tempNode.parent = node;
					}
					vertexQueue.enQueue(tempNode);
				}
				tempEdge = tempEdge.next;
			}
			node.colour = Colour.BLACK;
		}
	}

	public static Vertex[] createGraph() {
		Vertex[] inputGraph = new Vertex[8];
		inputGraph[0] = new Vertex("s");
		inputGraph[1] = new Vertex("r");
		inputGraph[2] = new Vertex("v");
		inputGraph[3] = new Vertex("w");
		inputGraph[4] = new Vertex("t");
		inputGraph[5] = new Vertex("x");
		inputGraph[6] = new Vertex("u");
		inputGraph[7] = new Vertex("y");
		createEdge(0, 1, 1, inputGraph, true);
		createEdge(0, 3, 1, inputGraph, true);
		createEdge(1, 2, 1, inputGraph, true);
		createEdge(3, 4, 1, inputGraph, true);
		createEdge(3, 5, 1, inputGraph, true);
		createEdge(4, 5, 1, inputGraph, true);
		createEdge(4, 6, 1, inputGraph, true);
		createEdge(5, 6, 1, inputGraph, true);
		createEdge(5, 7, 1, inputGraph, true);
		createEdge(6, 7, 1, inputGraph, true);
		return inputGraph;

	}

	// method to create an adjacency list
	public static void createEdge(int node1, int node2, int distance, Vertex[] inputGraph, boolean biDirectional) {
		Edge edge1 = inputGraph[node1].edge;
		Edge temp = new Edge();
		temp.weight = distance;
		temp.dest = inputGraph[node2];
		if (edge1 != null) {
			while (edge1.next != null) {
				edge1 = edge1.next;
			}
			edge1.next = temp;
		} else {
			inputGraph[node1].edge = temp;
		}
		if (biDirectional) {
			createEdge(node2, node1, distance, inputGraph, false);
		}
	}

}
