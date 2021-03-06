package Backend;

import java.util.Random;

import GUI.guiManager;

public class intensityMatrix {
	//od zrodla (1 tablica) do ujscia (2tablica) 
	private int [][] matrix ;
	public int size;
	public void generateMatrix(int matrixSize, int datasSize, int time,int left) {
		this.size = matrixSize;
		matrix = new int[size][size];
		Random rd = new Random();
		for(int i =0 ; i < size ; i++) {
			for (int j =0; j < size ; j++) {
				matrix[i][j] =( rd.nextInt(datasSize) +left)/time ;
			}
		}
		
	}
	public void showMatrix() {
		guiManager GUI = new guiManager(matrix,size);
		GUI.drawMatrix();
	}
	public int[][] getMatrix(){
		return matrix;
	}
	public void generateStaticMatrix(int matrixSize,int time,int left,int right) {
		int value =left;
		this.size = matrixSize;
		matrix = new int[size][size];
		for(int i =0 ; i < size ; i++) {
			for (int j =0; j < size ; j++) {
				matrix[i][j] =value/time ;
				value = value+1;
				if(value > right) {
					value = left;
				}
			}
		}
	}

}
