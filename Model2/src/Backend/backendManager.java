package Backend;

import java.util.ArrayList;
import java.util.Collection;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;


import Graphs.graphManager;

public class backendManager {
	private graphManager graphMG;
	public intensityMatrix IM;
	private DijkstraShortestPath<Integer,String> dsp;
	private capacityFunctions cf;
	private reliabilityManager rm;
	
	
	public backendManager(graphManager x) {
		this.graphMG = x;
		IM = new intensityMatrix();
	}
	public void start() {
		IM.generateMatrix(graphMG.getGraph().V.size(),31,1,1);
		IM.showMatrix();
		dsp = new DijkstraShortestPath<Integer, String>(graphMG.getGraph());
		//dsp.getPath(4, 11).getEdgeList().stream().forEach(System.out::println);;
		cf = new capacityFunctions(dsp,graphMG.getGraph(),IM.getMatrix());
		int count =0;
		int count2 =0;
		for(int i=0 ; i < 100; i++) {
			rm = new reliabilityManager(0.01,IM.getMatrix(),0.1,cf);
			if(cf.check()) {
				count2 = count2 +1;
				try {
					boolean x =rm.checkReliability(1);
					if(x) {
						count = count +1;
					}
				}
				catch(Exception e) {
					System.out.println("Doszlo do calkowitego wyizolowania pewnego wezla");
				}
			}
			System.out.println("Nastepny " + i);
		}
		System.out.println("Na 100 jest " + count + " przypadkow sukcesu " + count2);
	}
	public void firstExperiment() {
		IM.generateMatrix(graphMG.getGraph().V.size(),31,1);
	}
	
}
