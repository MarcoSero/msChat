

/**
	msChat: a simple Java-based chat for educational purposes.
	Copyright © Marco Sero
*/

import java.io.*;
import java.net.*;
import java.util.*;

public class msServer {
	
	static final int PORT = 8189;
	
	public static void main(String[] args ) {
		
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

				Scanner stin = new Scanner(System.in);
				
				System.out.println("Insert your nickname:");
				String chatName = stin.nextLine();
				
				System.out.println("\nConnection established!\n");
				out.println(chatName + " >> Connection established! Enter BYE to exit." );
	
				System.out.println("Waiting for client...");

	            // echo client input
				boolean done = false;
				while (!done && in.hasNextLine()) {
					
					// waiting for an input from client
					String line = in.nextLine();
					System.out.println(line);
					
					// waiting for an input
					System.out.print(chatName + " >> ");
					String lineout = stin.nextLine();
					out.println(chatName + " >> " + lineout);
					
					System.out.println("Waiting for client...");
					
					
				}
			}
			finally {
				System.out.println("Connection closed!");
				incoming.close();
			}
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }
}


