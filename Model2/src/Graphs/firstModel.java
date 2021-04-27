package Graphs;

import java.util.ArrayList;

public class firstModel extends myGraph{
	
	public firstModel(int vertexQuantity, int edgeQuantity) {
		this.V = new ArrayList<Integer>();
		this.E = new ArrayList<String>();
		generateVertex(vertexQuantity);
		generateMyFirstModel(edgeQuantity);
	}
	private void generateVertex(int vertexQuantity) {
		for(int i =1 ; i<= vertexQuantity; i++) {
			addVertex(i);
		}
	}
	private void generateMyFirstModel(int edgeQuantity) {
		generateFrame(1,3,18,20);
		generateLeaf(1,3,3);
		generateLeaf(4,6,3);
		generateLeaf(18,20,-3);
		generateLeaf(15,17,-3);
		generateFrame(7,9,12,14);
		addLine(7,10);
		addLine(12,10);
		addLine(10,11);
		addLine(14,11);
		addLine(9,11);
	}
	
	
	

}
