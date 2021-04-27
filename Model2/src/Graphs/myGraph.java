package Graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

public class myGraph implements Graph<Integer,String> {
	public ArrayList<Integer> V;
	public ArrayList<String> E;
	public void showGraph() {
		for(int i=0 ; i<E.size();i++) {
			System.out.println(E.get(i));
		}
		System.out.println(E.size());
	}
	protected void generateFrame(int left, int right, int left2, int right2) {
		int i = left;
		while(true) {
			//E.add(Integer.toString(i) + ";" + Integer.toString(i+1));
			addEdge(i,i+1);
			i = i+1;
			if(i ==right) {
				i = left2;
			//	E.add(Integer.toString(left) + ";" + Integer.toString(left2));
				addEdge(left,left2);
			}
			if(i == right2) {
				//E.add(Integer.toString(right) + ";" + Integer.toString(right2));
				addEdge(right,right2);
				break;
			}
		}
	}
	protected void generateLeaf(int left, int right, int distance) {
		int i = left -1;
		while(true) {
			i=i+1;
			//E.add(Integer.toString(i) + ";" + Integer.toString(i+distance));
			addEdge(i,i+distance);
			if(i == right) {
				break;
			}
		}
	}
	protected void addLine(int first, int second) {
		//E.add(Integer.toString(first) + ";" + Integer.toString(second));
		addEdge(first,second);
	}
	@Override
	public Set<String> getAllEdges(Integer sourceVertex, Integer targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getEdge(Integer sourceVertex, Integer targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Supplier<Integer> getVertexSupplier() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Supplier<String> getEdgeSupplier() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addEdge(Integer sourceVertex, Integer targetVertex) {
		E.add(Integer.toString(sourceVertex) + ";" + Integer.toString(targetVertex));
		return Integer.toString(sourceVertex) + ";" + Integer.toString(targetVertex);
	}
	@Override
	public boolean addEdge(Integer sourceVertex, Integer targetVertex, String e) {
		if(E.contains(e)) {
			return false;
		}
		E.add(Integer.toString(sourceVertex) + ";" + Integer.toString(targetVertex));
		return true;
	}
	@Override
	public Integer addVertex() {
		
		return null;
	}
	@Override
	public boolean addVertex(Integer v) {
		V.add(v);
		return true;
	}
	@Override
	public boolean containsEdge(Integer sourceVertex, Integer targetVertex) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsEdge(String e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsVertex(Integer v) {
		if(V.contains(v)) {
			return true;
		}
		return false;
	}
	@Override
	public Set<String> edgeSet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int degreeOf(Integer vertex) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Set<String> edgesOf(Integer vertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int inDegreeOf(Integer vertex) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Set<String> incomingEdgesOf(Integer vertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int outDegreeOf(Integer vertex) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Set<String> outgoingEdgesOf(Integer vertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean removeAllEdges(Collection<? extends String> edges) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Set<String> removeAllEdges(Integer sourceVertex, Integer targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean removeAllVertices(Collection<? extends Integer> vertices) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String removeEdge(Integer sourceVertex, Integer targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean removeEdge(String e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeVertex(Integer v) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Set<Integer> vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer getEdgeSource(String e) {
		String first = ""; 
		int j =0;
		while(e.charAt(j) != ';') {
			first = first + e.charAt(j);
			j =j+1;
		}
		return Integer.parseInt(first);
	}
	@Override
	public Integer getEdgeTarget(String e) {	
		String second = "";
		int j =0;
		while(e.charAt(j) != ';') {		
			j =j+1;
		}
		j =j+1;
		while(j<e.length()) {
			second = second + e.charAt(j);
			j=j+1;
		}
		return Integer.parseInt(second);
	}
	@Override
	public GraphType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getEdgeWeight(String e) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setEdgeWeight(String e, double weight) {
		// TODO Auto-generated method stub
		
	}
}
