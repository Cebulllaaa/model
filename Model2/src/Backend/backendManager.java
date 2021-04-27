package Backend;

import java.util.ArrayList;

import Graphs.graphManager;

public class backendManager {
	private graphManager graphMG;
	public intensityMatrix IM;
	private pathFounder pf;
	
	
	public backendManager(graphManager x) {
		this.graphMG = x;
		IM = new intensityMatrix();
	}
	public void start() {
		IM.generateMatrix(graphMG.getGraph().E.size(),31,1);
		//IM.showMatrix();
		pf = new pathFounder(graphMG.getGraph());
		ArrayList<Integer> Path = new ArrayList<Integer>();
		System.out.println(pf.foundPath(1, 9, Path, 0));
		//pf.showPath();
		
		
	}
}
