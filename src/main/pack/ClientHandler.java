package main.pack;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
	private Socket client;
	
	ClientHandler(Socket client){
		this.client = client;
	}
	
	OutputStreamWriter out = null;
	BufferedReader in = null;
	
	@Override
	public void run() {
		//client
		try {
			out = new OutputStreamWriter(client.getOutputStream());
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            int seconds = 3;//Waits for 3 seconds before input is received
            //I think this is what was asked?
            Thread.sleep(seconds*1000);
            
            //Receive
            String message = in.readLine();
        	System.out.println("---\nReceived: "+message+"\n---");

			//Send
            String sendMessage = "Message Accepted: "+message+"\n";
            
            out.write(sendMessage);
            out.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Thread dies
	}
}
