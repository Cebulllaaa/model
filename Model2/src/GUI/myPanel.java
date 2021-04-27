package GUI;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Graphs.graphManager;

public class myPanel extends JPanel{
	private Graphics g;
	public graphManager graphMG;
	private ArrayList<String> firstPoints = new ArrayList<String>();
	private ArrayList<String> secondPoints = new ArrayList<String>();
	public void drawGraph(graphManager graphMG) {
		this.graphMG = graphMG;
		setPoints();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int displacement =0;
		int startX =50;
		int startY = 50;
		ArrayList<Integer> pointsX = new ArrayList<Integer>();
		ArrayList<Integer> pointsY = new ArrayList<Integer>();
		for(int i=1; i<= graphMG.getGraph().V.size(); i++) {
			if(i ==4 || i ==15) {
				displacement = 50;
			}
			else if(i == 7 || i ==12) {
				displacement =100;
			}
			else if(i ==10) {
				displacement =200;
			}
			else if(i ==11) {
				displacement =150;
			}
			else if( i ==9 || i==14) {
				displacement = -100;
			}
			else if(i ==6 || i ==17) {
				displacement = -50;
			}
			else {
				displacement =0;
			}
			g.drawOval(startX+displacement, startY, 30, 30);
			g.drawString(Integer.toString(i), startX+displacement, startY);
			pointsX.add(startX+displacement);
			pointsY.add(startY);
			startX = startX+400;
			if((i%3 ==0 && i<10)|| i ==11 || (i>11 && i%3 ==2)) {
				startX=50;
				startY= startY+125;
			}
		}
		
		for(int i =0; i< firstPoints.size(); i++) {
			g.drawLine(pointsX.get(Integer.parseInt(firstPoints.get(i))-1) +15,pointsY.get(Integer.parseInt(firstPoints.get(i))-1)+15,
					pointsX.get(Integer.parseInt(secondPoints.get(i))-1) +15, pointsY.get(Integer.parseInt(secondPoints.get(i))-1)+15);
		}
	}
	private void setPoints() {
		for(int i =1; i<=graphMG.getGraph().E.size(); i++) {
			String first = ""; 
			String second = "";
			String code = graphMG.getGraph().E.get(i-1);
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
			//System.out.println(first + ";" + second);
			firstPoints.add(first);
			secondPoints.add(second);
		}
	}

	

}
