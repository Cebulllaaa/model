package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import Graphs.graphManager;

public class guiManager {
	private graphManager graphMG;
	
	public guiManager(graphManager x) {
		this.graphMG = x;
	}
	public void drawGraph() {
		JFrame frame = new JFrame();
		myPanel panel = new myPanel();
		panel.setBackground(Color.WHITE);
		panel.drawGraph(graphMG);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.setSize(1100,1000);
		frame.setVisible(true);
	}
	
}
