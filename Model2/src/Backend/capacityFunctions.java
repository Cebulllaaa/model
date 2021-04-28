package Backend;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import Graphs.myGraph;

public class capacityFunctions {
	private DijkstraShortestPath<Integer,String> dsp;
	private myGraph graph;
	private int[][] matrix;
	private List<String> path;
	public capacityFunctions(DijkstraShortestPath<Integer,String> dsp,myGraph graph,int[][] matrix) {
		this.dsp = dsp;
		this.graph = graph;
		this.matrix = matrix;
		
	}
	public int C(String edge) {
		return 1800;
	}
	public int A(String edge) {
		int result =0;
		int first ;
		int second ;
		for(int i=0; i< graph.V.size(); i++) {
			first = graph.V.get(i);
			for(int j=0; j< graph.V.size(); j++) {
				if(j!=i) {
					second = graph.V.get(j);
					path =dsp.getPath(first,second).getEdgeList();
					if(path.contains(edge)) {
						//System.out.println("Next");
						//System.out.println("First " + first + " second " + second);
						result = result + matrix[first-1][second-1];
						result = result + matrix[second-1][first-1];
						//dsp.getPath(first,second).getEdgeList().stream().forEach(System.out::println);
					}
					
				}
			}
		}
		return result;
	}

}
