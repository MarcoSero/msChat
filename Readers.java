import java.util.Scanner;
import java.io.*;

public class Readers {
	
	public volatile boolean done = false;
	
	private ReadKeyboard readK;
	private ReadSocket readS;
	
	public ReadKeyboard getReadKeyboard() {
		return readK;
	}
	
	public ReadSocket getReadSocket() {
		return readS;
	}
	
	public Readers(Scanner in, Scanner k, PrintWriter o, String n) {
		readK = new ReadKeyboard(k, o, n);
		readS = new ReadSocket(in, n);
	}
	
	/*
		Thread to read the input stream from keyboard
	*/
	
	public class ReadKeyboard extends Thread {
	
		private Scanner keyboard;
		private PrintWriter out;
		private String chatName;
	
		public ReadKeyboard(Scanner k, PrintWriter o, String n) {
			keyboard = k;
			out = o;
			chatName = n;
		}
	
		public void run() {
			
			while(!done) {
				
				System.out.print(chatName + " >> ");
				String lineout = keyboard.nextLine();
				if(lineout.equals("BYE")) {
					System.out.println("Waiting for closing connection on the other side...");
					out.println(lineout);
					done = true;
				}
				else {
					out.print(chatName + " >> ");
					out.println(lineout);
				}
			}
		}
	}
	
	/*
		Thread to read the input stream from socket
	*/
	
	public class ReadSocket extends Thread {

		private Scanner in;
		private String chatName;
	
		public ReadSocket(Scanner i, String c) {
			in = i;
			chatName = c;
		}
	
		public void run() {

			while(!done && in.hasNextLine()) {
				String line = in.nextLine();
				if(line.equals("BYE")) {
					System.out.println("\nThe other side want to close connection: press Enter to confirm...");
					done = true;
				}
				else {
					System.out.println("\n" + line);
					System.out.print(chatName + " >> ");
				}
			}
		}
	}
}