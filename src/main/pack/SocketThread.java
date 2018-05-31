package main.pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

class SocketThread implements Runnable{

	private static long NANOCONV = 1000000000;
	
	SocketThread(String hostName, String portNumber){
		/*Starting Client*/
	    try {
			client = new Socket(hostName, Integer.parseInt(portNumber));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(client!=null)
	    	System.out.println("Connection Failed! :'(\n");
	}
	
	private Socket client;
	
	OutputStreamWriter out = null;
	BufferedReader in = null;
	
	//socketThread(){}
	
	@Override
	public void run() {
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
	    
	    //---
	    
	    long startTime;
	    long endTime;
	    
	    //---
	    
	    try {
	        
	        int seconds = 5;//Waits for 5 seconds before input is Sent
	        //I think this is what was asked?
	        try {
				Thread.sleep(seconds*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //---
	    	//Send
	        startTime = System.nanoTime();
	        int  n = rand.nextInt(9) + 0; //0-9
	        out.write(msg[n]);
	        out.flush();
	        
	        endTime = System.nanoTime()-startTime;
	        System.out.println("Time Sent Messages Elapsed : "+endTime/NANOCONV+"(In Seconds)");
	        System.out.println("Time Sent Messages Elapsed : "+endTime+"(In NanoSecs)\n");
	        
	        //---
	        //*Receiving Messages*
	        startTime = System.nanoTime();
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        //Receive
	        String response = in.readLine();
	        System.out.println(response);
	        
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
	
	        System.out.println("\n\tClient End");
		}
	}
