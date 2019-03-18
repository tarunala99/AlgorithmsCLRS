package search;

enum Colour {
	WHITE, GREY, BLACK;
}

enum Type{
	TREE, BACK, FORWARD, CROSS;
}

class Vertex {
	String data = null;

	Vertex parent = null;

	int startTimeStamp = 0;
	
	int endTimeStamp = 0;

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
	
	Type type = null; 
}

class Node {
	
	Node next;
	
	char data;
}

public class DepthFirstSearch {

	public static int timeStamp = 0;
	
	public static void main(String[] args) {
		Vertex[] inputGraph = createGraph();		
		Node head = new Node();
		head = depthFirstSeach(inputGraph, head);
		StringBuilder newString = new StringBuilder();
		while(head!=null)
		{
			head = head.next;
			newString.append(head.data).append(" ->");
		}
		System.out.println(newString);
	}

	public static Node depthFirstSeach(Vertex[] inputGraph,Node head)
	{
		for(Vertex vertex : inputGraph)
		{
			if(vertex.colour == Colour.WHITE)
				head = iterativeSearch(vertex, head);
		}
		return head;
	}
	
	public static Node iterativeSearch(Vertex vertex, Node head)
	{
		vertex.colour = Colour.GREY;
		timeStamp++;
		vertex.startTimeStamp = timeStamp;
		//iterativeSearch find the index of the vertex
		//set the parent as well
		Edge edge = vertex.edge;
		while(edge!=null)
		{
			Vertex tempVertex = vertex.edge.dest;
			if(tempVertex.colour==Colour.WHITE)//Once discovered they are no longer added to the stack
			{
				edge.type = Type.TREE; //Basically if the current node becomes the parent of the called node
				tempVertex.parent = vertex;
				head = iterativeSearch(tempVertex, head);
			}
			else
			{
				// grey implies it has been discovered in the current chain
				if(tempVertex.colour==Colour.GREY)
				{
					edge.type = Type.BACK; //Grey implying has already been called; hence the back type
				}
				else // When it is black it has already been discovered
				{
					//Forward implies it is not a direct descendant and rather it is somewhere in the chain
					//This implies the start and end of that node have already occurred
					if(vertex.startTimeStamp < tempVertex.startTimeStamp)
					{
						edge.type = Type.FORWARD; 
					}
					edge.type = Type.CROSS; 
				}
			}
			edge = edge.next;
		}
		timeStamp++;
		vertex.colour = Colour.BLACK;
		head.data = vertex.data.charAt(0);
		Node node = new Node();
		node.next = head;
		head = node;
		vertex.endTimeStamp=timeStamp;
		return head;
	}

	public static Vertex[] createGraph() {
		Vertex[] inputGraph = new Vertex[6];
		inputGraph[0] = new Vertex("u");
		inputGraph[1] = new Vertex("v");
		inputGraph[2] = new Vertex("w");
		inputGraph[3] = new Vertex("x");
		inputGraph[4] = new Vertex("y");
		inputGraph[5] = new Vertex("z");
		createEdge(0, 1, 1, inputGraph, false);
		createEdge(0, 3, 1, inputGraph, false);
		createEdge(3, 1, 1, inputGraph, false);
		createEdge(1, 4, 1, inputGraph, false);
		createEdge(2, 4, 1, inputGraph, false);
		createEdge(4, 3, 1, inputGraph, false);
		createEdge(2, 5, 1, inputGraph, false);
		createEdge(5, 5, 1, inputGraph, false);
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
