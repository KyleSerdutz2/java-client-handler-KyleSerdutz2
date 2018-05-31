package main.pack;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.lang.*;

public class Client {
	
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
        System.out.println("\tClient Started");
        try {
            /*ServerIP and PortNumber*/
            boolean bServerPortDone = false;
            while(!bServerPortDone) {
                System.out.println("\nCurrent ServerIP is: \t"+ hostName);
                System.out.println("Current PortNumber is: \t"+ portNumber);
               
                System.out.println("\nWould you like to make any alterations?");
                System.out.print("[y/n]: ");
                
                input = new Scanner(System.in);
                char yn = input.nextLine().charAt(0);
                if(yn=='y') {
                    System.out.print("\nServerIP: ");
                    hostName = input.nextLine(); //Input is a "\n"
                    System.out.print("PortNumber: ");
                    portNumber = input.nextLine();
                }else if(yn=='n') {
                    bServerPortDone = true;
                }else {
                    System.out.println("\nInvalid Input");
                }
            }
            
            /*Starting Client*/
            client = new Socket(hostName, Integer.parseInt(portNumber));
            if(client!=null)
            	bHasConnected = true;
            /*Receiving Message*/
//          read text from a Socket’s InputStream and 
//          respond to the Socket’s OutputStream 
//          with “Message Accepted: {message}” where {message} is the 
//          text input received from the InputStream
            
            /*Change Message*/
            String sendMessage = "Hello World!";
            boolean sendMessageDone = false;
            while(!sendMessageDone) {
                System.out.println("\nCurrent Message is: \t"+ sendMessage);
               
                System.out.println("\nWould you like to make any alterations?");
                System.out.print("[y/n]: ");
                
                input = new Scanner(System.in);
                char yn = input.nextLine().charAt(0);
                if(yn=='y') {
                    System.out.print("\nMessage: ");
                    sendMessage = input.nextLine(); //Input is a "\n"
                }else if(yn=='n') {
                	sendMessageDone = true;
                }else {
                    System.out.println("\nInvalid Input");
                }
            }
            sendMessage+="\n";
            
            
            out = new OutputStreamWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            //Send
            out.write(sendMessage);
            out.flush();
            
            //Receive
            String response = in.readLine();
            System.out.println(response);
            
        } catch (IOException ex) {
        	ex.printStackTrace();
        } finally {
        	input.close();
        	try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
            
        if(!bHasConnected)
            System.out.println("\tConnection Failed!!! :'(");

        System.out.println("\n\tClient End");
	}
}
