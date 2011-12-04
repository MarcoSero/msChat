
/**
	msChat: a simple Java-based chat for educational purposes.
	Copyright © Marco Sero
*/


import java.io.*;
import java.net.*;
import java.util.*;


public class msClient {
	
	static final int PORT = 8189;
	
	public static void main(String[] args) throws InterruptedException {
		try {
			
			/////////////////// SET BELOW VARIABLE ///////////////////
			final String IP = InetAddress.getLocalHost().getHostName();
			/////////////////// SET ABOVE VARIABLE ///////////////////
			
         	System.out.println("Trying to start connection with server on '" + IP + "'...");
         	Socket s = new Socket(IP, PORT);
			
			try {
				
	            InputStream inStream = s.getInputStream();
	            Scanner in = new Scanner(inStream);
	            OutputStream outStream = s.getOutputStream();
	            PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
	            Scanner keyboard = new Scanner(System.in);
	
				System.out.println("Insert your nickname:");
				String chatName = keyboard.nextLine();
				
	            String line = in.nextLine(); // waiting for OK from server
	            System.out.println(line);

	
				Readers read = new Readers(in, keyboard, out, chatName);
				Readers.ReadSocket readS = read.getReadSocket();
				Readers.ReadKeyboard readK = read.getReadKeyboard();
				
				readS.start();
				readK.start();
				readS.join();
				readK.join();
				
	        } finally {
            	s.close();
         	}
      	} catch (ConnectException err) {
			System.out.println("You have to start msServer first.");
		} catch (IOException e) {
         	e.printStackTrace();
      	}
   }
}
