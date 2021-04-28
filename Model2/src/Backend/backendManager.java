package Backend;

import java.util.ArrayList;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import Graphs.graphManager;

public class backendManager {
	private graphManager graphMG;
	public intensityMatrix IM;
	private DijkstraShortestPath<Integer,String> dsp;
	
	
	public backendManager(graphManager x) {
		this.graphMG = x;
		IM = new intensityMatrix();
	}
	public void start() {
		IM.generateMatrix(graphMG.getGraph().V.size(),31,1);
		IM.showMatrix();
		dsp = new DijkstraShortestPath<Integer, String>(graphMG.getGraph());
		dsp.getPath(1,14).getEdgeList().stream().forEach(System.out::println);
		//pf.showPath();
		
		
	}
}
