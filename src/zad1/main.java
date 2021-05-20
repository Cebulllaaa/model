package zad1;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
	File file;
	public static void main(String[] args) {
		codeManager manager = new codeManager(args[0]);
		try {
			manager.code();
			manager.decode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
