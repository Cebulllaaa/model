package Graphs;

import Backend.backendManager;
import GUI.guiManager;

public class main {
	private static graphManager graphMG;
	private static guiManager GM;
	private static backendManager BM;
	public static void main(String[] args) {
		graphMG = new graphManager();
		graphMG.ui();
		//graphMG.getGraph().showGraph();
		GM = new guiManager(graphMG);
		GM.drawGraph();
		BM = new backendManager(graphMG);
		BM.start();
		
	}
}
