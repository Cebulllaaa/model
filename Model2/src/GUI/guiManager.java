package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import Graphs.graphManager;

public class guiManager {
	private graphManager graphMG;
	private int[][] matrix;
	private int matrixSize;
	
	public guiManager(graphManager x) {
		this.graphMG = x;
	}
	public guiManager(int[][] matrix, int matrixSize) {
		this.matrix = matrix;
		this.matrixSize = matrixSize;
		
	}
	public void drawGraph() {
		JFrame frame = new JFrame();
		graphPanel panel = new graphPanel();
		panel.setBackground(Color.WHITE);
		panel.drawGraph(graphMG);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.setSize(1100,1000);
		frame.setVisible(true);
	}
	public void drawMatrix() {
		JFrame frame = new JFrame();
		matrixPanel panel = new matrixPanel();
		panel.setBackground(Color.WHITE);
		panel.drawMatrix(matrix,matrixSize);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
	
}
