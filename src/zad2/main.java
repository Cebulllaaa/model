package zad2;

import zad1.codeManager;

public class main {
	private static simulation sim;
	public static void main(String[] args) {
		int time = Integer.parseInt(args[0]);
		sim = new simulation(time);
		try {
			sim.start();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
