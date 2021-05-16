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
	public void firstExperiment() {
		
		IM.generateStaticMatrix(graphMG.getGraph().V.size(),1,32,37);
		IM.showMatrix();
		dsp = new DijkstraShortestPath<Integer, String>(graphMG.getGraph());
		//dsp.getPath(4, 11).getEdgeList().stream().forEach(System.out::println);;
		cf = new capacityFunctions(dsp,graphMG.getGraph(),IM.getMatrix());
		cf.generateStream();
		int countSucces =0;
		int capacityErrors =0;
		int delayErrors= 0;
		int breakErrors=0;
		for(int i=0 ; i < 100; i++) {
			rm = new reliabilityManager(0.01,IM.getMatrix(),0.07,cf, IM.size);
				try {
					int x =rm.checkReliability(1);
					if(x==1) {
						countSucces = countSucces +1;
					}
					else if(x ==0) {
						delayErrors = delayErrors +1;
					}
					else if(x ==-1) {
						capacityErrors = capacityErrors+1;
					}
					else {
						breakErrors = breakErrors +1;
					}
				}
				catch(Exception e) {
					System.out.println(e);
				}
				System.out.println("Nastepny " + i);
			}
		System.out.println("Na 100 jest " + countSucces + " przypadkow sukcesu ");
		System.out.println(delayErrors + " przypadkow keidy T >= T_max");
		System.out.println(capacityErrors + " przypadkow kiedy C(edge < A(edge)");
		System.out.println(breakErrors + " przypadkow keidy doszlo do przerwania sieci");
		
	}
	public void corrected() {
		IM.generateStaticMatrix(graphMG.getGraph().V.size(),1,32,37);
		IM.showMatrix();
		dsp = new DijkstraShortestPath<Integer, String>(graphMG.getGraph());
		cf = new capacityFunctions(dsp,graphMG.getGraph(),IM.getMatrix());
		cf.generateStream();
	}
	
}
