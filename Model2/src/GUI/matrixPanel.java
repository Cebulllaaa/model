package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class matrixPanel extends JPanel{
	private int[][] matrix;
	private int matrixSize;
	public void drawMatrix(int[][] matrix,int matrixSize) {
		this.matrix = matrix;
		this.matrixSize = matrixSize;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int recSize= 400/matrixSize;
		
		for(int i=1 ; i<=20; i++) {
			g.drawRect(i*matrixSize, 0, recSize, recSize);
			g.setColor(Color.black);
			g.drawString(Integer.toString(i), i*matrixSize, 10);
		}
		for(int i=1 ; i<=20; i++) {
			g.drawRect(0, i*matrixSize, recSize, recSize);
			g.drawString(Integer.toString(i), 0, i*matrixSize+10);
		}

		for(int i=1; i<=20;i++) {
			for(int j=1; j<=20;j++) {
				g.drawRect(i*matrixSize, j*matrixSize, recSize, recSize);
				g.drawString(Integer.toString(matrix[i-1][j-1]), i*matrixSize, j*matrixSize+10);
			}
		}
	
		
	}
}
