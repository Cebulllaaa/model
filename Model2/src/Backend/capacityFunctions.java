package Backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import Graphs.myGraph;

public class capacityFunctions {
	private DijkstraShortestPath<Integer,String> dsp;
	private myGraph graph;
	private int[][] matrix;
	private List<String> path;
	private Map<String, Integer> stream ;
	public capacityFunctions(DijkstraShortestPath<Integer,String> dsp,myGraph graph,int[][] matrix) {
		this.dsp = dsp;
		this.graph =  (myGraph) graph;
		this.matrix = matrix;
		stream = new HashMap<String, Integer>();
		
	}
	public int C(String edge) {
		return 120000;
	}
	public void generateStream() {
		for (String edge : graph.E) 
		{ 
			stream.put(edge, A(edge));
		}
	}
	public int getStream(String edge) {
		return stream.get(edge);
	}
	private int A(String edge) {
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
						result = result + matrix[first-1][second-1];
						result = result + matrix[second-1][first-1];
						//dsp.getPath(first,second).getEdgeList().stream().forEach(System.out::println);
					}
					
				}
			}
		}
		return result;
	}
	public boolean check() {
		for (String edge : graph.E) 
		{ 
			if(C(edge) < stream.get(edge)) {
				return false;
			}
		}
		return true;
	}

}
