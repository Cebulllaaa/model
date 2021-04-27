package Graphs;

public class graphManager {
	private myGraph graph;
	public void ui() {
		firstImplementation();
	}
	private void firstImplementation() {
		graph = new firstModel(20,20);
		
	}
	public myGraph getGraph() {
		return graph;
	}
}
