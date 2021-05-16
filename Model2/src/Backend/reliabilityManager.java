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
	public boolean checkReliability(int packageSize) {
		double T;
		accident();
		//dodaj sprawdzenie czy siec jest rozerwana
		//dodaj tu sprawdzenie czy C(edge) > A(edge)
		T= SUM_e(packageSize) ;
		T= T/getMatrixSum();
		System.out.println(T);
		return (T<T_max && T>=0);
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
			//Raz wyliczyc przeplyw i zapisac 
			result = result + (cf.A(graph.E.get(i)) / ((cf.C(graph.E.get(i))/getAverage())-cf.A(graph.E.get(i))   ));
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
		int result= getMatrixSum()/size;
		result = result /size;
		//System.out.println("Result " + result);
		return result;
	}
}
