

/**
	msChat: a simple Java-based chat for educational purposes.
	Copyright © Marco Sero
*/

import java.io.*;
import java.net.*;
import java.util.*;

public class msServer {
	
	static final int PORT = 8189;
	
	public static void main(String[] args ) throws InterruptedException {
		
		try {
			
			// establish server socket
			System.out.println("Starting server on port " + PORT);
			ServerSocket s = new ServerSocket(PORT);
			
			// wait for client connection
			System.out.println("Waiting for client connection...");
			Socket incoming = s.accept();
			
			try {
				System.out.println("Setting up streams...");
				InputStream inStream = incoming.getInputStream();
	            OutputStream outStream = incoming.getOutputStream();
	
				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

				Scanner keyboard = new Scanner(System.in);
				
				System.out.println("Insert your nickname:");
				String chatName = keyboard.nextLine();
				
				System.out.println("\nConnection established!\n");
				out.println(chatName + " >> Connection established! Enter BYE to exit." );
	
				System.out.println("Waiting for client...");


				Readers read = new Readers(in, keyboard, out, chatName);
				Readers.ReadSocket readS = read.getReadSocket();
				Readers.ReadKeyboard readK = read.getReadKeyboard();
									
				readS.start();
				readK.start();
				
				readS.join();
				readK.join();
	
			}
			finally {
				System.out.println("Connection closed!");
				incoming.close();
			}
      	} catch (ConnectException err) {
			System.out.println("You have to start msServer first.");
		} catch (IOException e) {
         	e.printStackTrace();
      	}
   }
}

