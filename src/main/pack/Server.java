package main.pack;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

/*
Client Handler

Create a ServerSocket that accepts Socket connections[clients].
After accepting the Socket connection[client],
pass the Socket[client] to a new ClientHandler thread and start that Thread.
The ServerSocket will then begin awaiting a new connection.

The client will wait 3 seconds then send a message to the server,
to which the server will respond with “Message Accepted: {message}”.

Create a test case that will spawn 10 client requests,
and wait 5 seconds to receive responses from each of the client requests.

Task List
1[x]
Create a ClientHandler class that will read text from a 
Socket’s InputStream and respond to the Socket’s OutputStream 
with “Message Accepted: {message}” where {message} is the 
text input received from the InputStream
2[x]
Create a main method that will start a ServerSocket 
that continuously listens for incoming connections
3[x]
When a connection is received, instantiate a ClientHandler and 
pass the received Socket to that ClientHandler Resume 
listening for incoming connections while the 
ClientHandler waits for a message from the connected Socket
4[]
Create a test that will spawn 10 Sockets that connect to the server
	4b[]
Ensure that each Socket will connect to the server, 
wait 3 seconds, then send a message of your choosing
	4c[]
Ensure that responses are received for all 10 messages sent within 10 seconds
*/

/*
	Server
		ClientHandler(Thread)
			Client
		ClientHandler(Thread)
			Client
		ClientHandler(Thread)
			Client
		ClientHandler(Thread)
			Client
*/

public class Server {

	public static void main(String[] args) {
		
        String portNumber = "10777";
        
        ServerSocket server = null;
        Scanner input = null;
        
        ArrayList<Thread> clientHandlers = null;
        
        System.out.println("\tServer Started");
        try {
            /*ServerIP and PortNumber*/
            boolean bServerPortDone = false;
            while(!bServerPortDone) {
                System.out.println("Current PortNumber is: \t"+ portNumber);
               
                System.out.println("\nWould you like to make any alterations?");
                System.out.print("[y/n]: ");
                
                input = new Scanner(System.in);
                char yn = input.nextLine().charAt(0);
                if(yn=='y') {
                    System.out.print("PortNumber: ");
                    portNumber = input.nextLine();
                }else if(yn=='n') {
                    bServerPortDone = true;
                }else {
                    System.out.println("\nInvalid Input");
                }
            }
            
            /*Starting Server*/
            server = new ServerSocket(Integer.parseInt(portNumber));
            
            int seconds = 20;
            server.setSoTimeout(seconds*1000);
            
            /*Client Threads*/
            //This thread takes in stuff
//            Thread serverWaiter = new Thread(new ServerWaiter(server));
//            serverWaiter.run();
            //While Server lets you terminate program at will

            
            Socket client = null;
            Thread newThread = null;
            clientHandlers = new ArrayList<Thread>();
            //Program ends based on an Exception Thrown
        	System.out.println("\n\tAccepting Clients...\n");
            while(true) {
            	client = server.accept();
            	//If(Program didn't crash)
            		System.out.println("Client Joined...");
	            	newThread = (new Thread(new ClientHandler(client)));
	            	newThread.start();
	            	clientHandlers.add(newThread);
            }
            //Enter any key to terminate
            //input.nextLine().charAt(0);
//            serverWaiter.join();
        } //catch (IOException ex) {
        //stack.
        //} 
        	catch (Exception ex) {
        		ex.printStackTrace();
        } finally {
        	input.close();
            //System.out.println("\n\tException Raised");
        	try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        /*Join Threads Before End*/
        //Is this even neccesary?
        /*
        for(int i = 0; i < clientHandlers.size(); i++) {
        	if (clientHandlers.get(i) != null) {
        		try {
					clientHandlers.get(i).join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
		}
		*/
        System.out.println("\n\tServer End");
	}
}
