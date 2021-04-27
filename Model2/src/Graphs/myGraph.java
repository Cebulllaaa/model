package Graphs;

import java.util.ArrayList;

import org.jgrapht.Graph;

public abstract class myGraph {
	public ArrayList<Integer> V;
	public ArrayList<String> E;
	public void showGraph() {
		for(int i=0 ; i<E.size();i++) {
			System.out.println(E.get(i));
		}
		System.out.println(E.size());
	}
	protected void generateFrame(int left, int right, int left2, int right2) {
		int i = left;
		while(true) {
			E.add(Integer.toString(i) + ";" + Integer.toString(i+1));
			//addEdge(i,i+1);
			i = i+1;
			if(i ==right) {
				i = left2;
				E.add(Integer.toString(left) + ";" + Integer.toString(left2));
			//	addEdge(left,left2);
			}
			if(i == right2) {
				E.add(Integer.toString(right) + ";" + Integer.toString(right2));
			//	addEdge(right,right2);
				break;
			}
		}
	}
	protected void generateLeaf(int left, int right, int distance) {
		int i = left -1;
		while(true) {
			i=i+1;
			E.add(Integer.toString(i) + ";" + Integer.toString(i+distance));
			//addEdge(i,i+distance);
			if(i == right) {
				break;
			}
		}
	}
	protected void addLine(int first, int second) {
		E.add(Integer.toString(first) + ";" + Integer.toString(second));
		//addEdge(first,second);
	}
}
