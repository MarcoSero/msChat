
/**
	msChat: a simple Java-based chat for educational purposes.
	Copyright © Marco Sero
*/


import java.io.*;
import java.net.*;
import java.util.*;


public class msClient {
	
	static final int PORT = 8189;
	
	public static void main(String[] args) {
		try {
			
			final String IP = InetAddress.getLocalHost().getHostName();
         	System.out.println("Starting chat on: '" + IP + "' as client");
         	Socket s = new Socket(IP, PORT);
			
			try {
				
	            InputStream inStream = s.getInputStream();
	            Scanner in = new Scanner(inStream);
	            OutputStream outStream = s.getOutputStream();
	            PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
	            Scanner stin = new Scanner(System.in);
	
				System.out.println("Insert your nickname:");
				String chatName = stin.nextLine();
				
	            String line = in.nextLine(); // waiting for OK from server
	            System.out.println(line);

	            boolean done = false;
	            while (!done) { // && in.hasNextLine())
					
					// waiting for an input
					System.out.print(chatName + " >> ");
					String lineout = stin.nextLine();
					out.println(chatName + " >> " + lineout);
					
					if (lineout.equals("BYE")) {
						done = true;
						break;
					}
					
					// waiting for an input from server
					System.out.println("Waiting for server...");
					line = in.nextLine();
					System.out.println(line);

	            }
	         }
         finally
         {
            s.close();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
