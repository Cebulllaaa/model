package Backend;

import java.util.Random;

import Graphs.firstModel;
import Graphs.myGraph;

public class reliabilityManager {
	private double p;
	private int[][] matrix;
	private double T_max;
	private firstModel graph;
	private capacityFunctions cf;
	private int size;
	public reliabilityManager(double p, int[][]matrix , double T_max, capacityFunctions cf,int size) {
		this.p =p;
		this.matrix = matrix;
		this.T_max = T_max;
		this.graph = new firstModel(20,20);
		this.cf =cf;
		this.size = size;
	}
	public int checkReliability(int packageSize) {
		double T;
		//dodaj sprawdzenie czy siec jest rozerwana => poprawione
		//dodaj tu sprawdzenie czy C(edge) > A(edge) => poprawione
		accident();
		boolean breakCorrect =checkBreak();
		if(!breakCorrect) {
			return -2;
		}
		boolean capacityCorrect = checkCapacity();
		
		if(!capacityCorrect) {
			return -1;
		}
		T= SUM_e(packageSize) ;
		T= T/getMatrixSum();
		System.out.println(T);
		if( T < T_max ) {
			return 1;
		}
		else  {
			return 0;
		}
		
	}
	private int getMatrixSum() {
		int result =0;
		for(int i=0; i< graph.V.size(); i++) {
			for(int j=0; j< graph.V.size(); j++) {
				result = result + matrix[i][j];
			}
			
		}
		return result;
	}
	private double SUM_e(int packageSize) {
		double result =0;
		for(int i=0; i< graph.E.size(); i++) {
			//Zamiast wyliczac A przy kazdym wywolaniu, wyliczyc raz i zapisac => poprawione
			result = result + (cf.getStream(graph.E.get(i)) / ((cf.C(graph.E.get(i))/getAverage())-cf.getStream(graph.E.get(i))   ));
		}
		
		return result;
	}
	public void accident() {
		double chance ;
		for(int i=0; i< graph.E.size(); i++) {
			chance = Math.random();
			if(chance > 1-p) {
				graph.E.remove(i);
			}
		}
		
		
		
	}
	public int getAverage() {
	//	int result= getMatrixSum()/size;
	//	result = result /size;
	//	return result;
		return 1;
	
	}
	private boolean checkBreak() {
		for (int vertex : graph.V) 
		{ 
			boolean empty = true;
			for(int i=0; i< graph.V.size(); i++) {
				
				if(graph.containsEdge(vertex, graph.V.get(i))) {
					empty = false;
					i = graph.V.size();
				}
			}
			if(empty) {
				return false;
			}
		}
		return true;
	}
	private boolean checkCapacity() {
		return cf.check();
	}
}
