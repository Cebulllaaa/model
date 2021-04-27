package Backend;

import java.util.ArrayList;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import Graphs.myGraph;

public class pathFounder {
	private myGraph graph;
	private ArrayList<Integer> Path;
	private ArrayList<Integer> firstPoints;
	private ArrayList<Integer> secondPoints;
	
	public pathFounder(myGraph graph) {
		this.graph = graph;
		firstPoints = new ArrayList<Integer>();
		secondPoints = new ArrayList<Integer>();
		setPoints();
		Path = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getPath() {
		return Path;
	}
	public void setPath(ArrayList<Integer> path) {
		Path = path;
	}
	
	public void foundPath(int start, int end, ArrayList<Integer> oldPlaces, int distance) {
		/*if(start == end) {
			return distance +1;
		}
		ArrayList<Integer> distances = new ArrayList<Integer>();
		
		oldPlaces.add(start);
		Path.add(start);
		for(int i=0; i< secondPoints.size(); i++) {
			if(firstPoints.get(i)== start && (oldPlaces.contains(secondPoints.get(i))== false)) {
				distances.add(foundPath(secondPoints.get(i),end,oldPlaces,distance));
			}
		}
		int min=0;
		for(int i =0 ; i< distances.size(); i++) {
			if(i ==0 ||distances.get(i) < min ) {
				min = distances.get(i);
			}
		}
		distance = distance +min ;
		return distance;
		*/
		DijkstraShortestPath<Integer,String> dsp = new DijkstraShortestPath<Integer, String>(graph);
		dsp.getPath(1,11);
		//dsp.getPath(1,11).getEdgeList().stream().forEach(System.out::println);
	}
	public void showPath() {
		for(int i =0; i< Path.size(); i++) {
			System.out.println(Path.get(i));
		}
	}
	private void setPoints() {
		for(int i =1; i<=graph.E.size(); i++) {
			String first = ""; 
			String second = "";
			String code = graph.E.get(i-1);
			int j =0;
			while(code.charAt(j) != ';') {
				first = first + code.charAt(j);
				j =j+1;
			}
			j =j+1;
			while(j<code.length()) {
				second = second + code.charAt(j);
				j=j+1;
			}
			firstPoints.add(Integer.parseInt(first));
			secondPoints.add(Integer.parseInt(second));
		}
	}

}
