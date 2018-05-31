package main.pack;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;

/*
4[x]
Create a test that will spawn 10 Sockets that connect to the server
	4b[]
Ensure that each Socket will connect to the server, 
wait 3 seconds, then send a message of your choosing
	4c[]
Ensure that responses are received for all 10 messages sent within 10 seconds
*/


public class Test10Socket {
	/* 2nd Attempt
		public static void main(String[] args) {
			String hostName = "127.0.0.1";
			String portNum = "10777";
			
			Thread thread01 = new Thread(new SocketThread(hostName, portNum));
			Thread thread02 = new Thread(new SocketThread(hostName, portNum));
			Thread thread03 = new Thread(new SocketThread(hostName, portNum));
			Thread thread04 = new Thread(new SocketThread(hostName, portNum));
			Thread thread05 = new Thread(new SocketThread(hostName, portNum));
			Thread thread06 = new Thread(new SocketThread(hostName, portNum));
			Thread thread07 = new Thread(new SocketThread(hostName, portNum));
			Thread thread08 = new Thread(new SocketThread(hostName, portNum));
			Thread thread09 = new Thread(new SocketThread(hostName, portNum));
			Thread thread10 = new Thread(new SocketThread(hostName, portNum));
			

			thread01.start();
			thread02.start();
			thread03.start();
			thread04.start();
			thread05.start();
			thread06.start();
			thread07.start();
			thread08.start();
			thread09.start();
			thread10.start();
			try{
				thread01.join();
				thread02.join();
				thread03.join();
				thread04.join();
				thread05.join();
				thread06.join();
				thread07.join();
				thread08.join();
				thread09.join();
				thread10.join();
			} catch (InterruptedException ex) {
				
			}
		}
	}
	*/
	
	//First attempt at 10SocketTest
	//Messed up by not taking use of multithreading
	
	private static long NANOCONV = 1000000000;
	
	private static InputStream is = null;
	private static OutputStream os = null;
	
	public static void main(String[] args) {
		boolean bHasConnected = false;
		
		String hostName = "127.0.0.1";
        String portNumber = "10777";
        
        OutputStreamWriter out = null;
        BufferedReader in = null;
        
        Socket client = null;
        Scanner input = null;

        System.out.println("\tTest Started");
        try {
        	//---
        	
	        //*Starting Clients*
	        Socket[] clients = new Socket[10];
	        for(int i = 0; i < clients.length; i++)
	        	clients[i] = new Socket(hostName, Integer.parseInt(portNumber));
	        
	        //---
	        
	        long startTime;
	        long endTime;
	        
	        //---
	        
	        //*Sending Messages*
	        String[] msg = new String[10];
	        msg[0] = "See ya\n";
	        msg[1] = "What's up?\n";
	        msg[2] = "How are you?\n";
	        msg[3] = "Love you~\n";
	        msg[4] = "Miss you~\n";
	        msg[5] = "Farewell\n";
	        msg[6] = "Okay...\n";
	        msg[7] = "Huh?\n";
	        msg[8] = "Yes!\n";
	        msg[9] = "No, not really\n";
	        Random rand = new Random();
	        startTime = System.nanoTime();
	        for(int i = 0; i < clients.length; i++) {
	        	out = new OutputStreamWriter(clients[i].getOutputStream());
	        	clients[i] = new Socket(hostName, Integer.parseInt(portNumber));
	        	
	        	int  n = rand.nextInt(9) + 0; //0-9
	        	//Send
	            out.write(msg[n]);
	            out.flush();
	        }
	        endTime = System.nanoTime()-startTime;
            System.out.println("Time Sent Messages Elapsed : "+endTime/NANOCONV+"(In Seconds)");
            System.out.println("Time Sent Messages Elapsed : "+endTime+"(In NanoSecs)\n");
            
            //---

	        //*Receiving Messages*
	        startTime = System.nanoTime();
	        for(int i = 0; i < clients.length; i++) {
	        	System.out.println(i);
	        	//Freezes up here because not multithreaded
	            //Waits for input that won't come/has already came
	        	
				in = new BufferedReader(new InputStreamReader(clients[i].getInputStream()));
	            //Receive
	            String response = in.readLine();
	            System.out.println(response);
	        }
	        endTime = System.nanoTime()-startTime;
            System.out.println("Time Received Messages Elapsed : "+endTime/NANOCONV+"(In Seconds)");
            System.out.println("Time Sent Messages Elapsed : "+endTime+"(In NanoSecs)\n");
	        		
            //---
            
        } catch (IOException ex) {
        	ex.printStackTrace();
        } finally {
        	try {
                out.close();
                in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if(!bHasConnected)
            System.out.println("\tConnection Failed!!! :'(");

        System.out.println("\n\tClient End");
	}
}//
