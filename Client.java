package lab_udp_server;

import java.io.*;
import java.net.*;

public class Client {
	
	String frendName;
	
	public static void main(String[] argv) {
		try {
			InputStream inputStream = System.in;
			Reader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			if(argv.length < 3) {
				throw new Exception("please enter your port, frend port and frends ip address");
			}
			
			int my_port = Integer.parseInt(argv[0]);
			
			int sender_port = Integer.parseInt(argv[1]);
			
			DatagramSocket clientSocket = new DatagramSocket(my_port);
			
			InetAddress ipAddress = InetAddress.getByName(argv[2]);
			
			Receiver receiver = new Receiver(clientSocket);
			Sender sender = new Sender(clientSocket, ipAddress, sender_port);
			receiver.start();
			sender.start();
			
			
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
		}
		
		
	}
	
}
