package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
	static BasicUI ui;
	
	public static void main(String[] args) {
		class OnExit implements Runnable {

			@Override
			public void run() {
				try {
					ui.save();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.err.print("Warning: entries did not properly save on exit");
				}
			}
		}
		
		// saves to file on exit
		// please dont touch this
		Runtime.getRuntime().addShutdownHook(new Thread(new OnExit()));
		
		Scanner sc = new Scanner(System.in);
		ui = new BasicUI(sc);
		
		try {
			ui.load();
		} catch (IOException e) {
			System.err.print("Warning: entries did not load properly, running without save file");
		}
		
		ui.run();
	}
}
