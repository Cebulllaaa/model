package Graphs;

public class graphManager {
	private myGraph graph;
	public void ui() {
		firstImplementation();
	}
	private void firstImplementation() {
		graph = new firstModel(20,20);
		//graph.addEdge(2,11);
		//graph.addEdge(15, 17);
		
		
	}
	public myGraph getGraph() {
		return graph;
	}
}
