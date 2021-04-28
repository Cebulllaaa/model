package Backend;

import java.util.Random;

import GUI.guiManager;

public class intensityMatrix {
	//od zrodla (1 tablica) do ujscia (2tablica) 
	private int [][] matrix ;
	private int size;
	public void generateMatrix(int matrixSize, int datasSize, int time) {
		this.size = matrixSize;
		matrix = new int[size][size];
		Random rd = new Random();
		for(int i =0 ; i < size ; i++) {
			for (int j =0; j < size ; j++) {
				matrix[i][j] =( rd.nextInt(datasSize) +1)/time ;
			}
		}
		
	}
	public void showMatrix() {
		guiManager GUI = new guiManager(matrix,size);
		GUI.drawMatrix();
		
	}

}
