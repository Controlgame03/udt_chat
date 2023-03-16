package lab_udp_server;
import java.net.*;
import java.io.*;

public class Sender extends Thread {
	DatagramSocket currentSocket;
	InetAddress ipAddress;
	int port;
	
	private static final int PACKET_SIZE = 1024;
	
	Sender(DatagramSocket socket, InetAddress ipAddress, int receiverPort){
		try{
			if (socket.isClosed()) {
	            throw new Exception("Cannot open senger socket");
	        }
			currentSocket = socket;
			this.ipAddress = ipAddress;
			port = receiverPort;
		}
		catch(Exception excep) {
			System.out.println(excep.getMessage());
		}
	}
	
	public void run(){
		String name = "unknown";
		while(true) {
			try {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("message ---> ");
				
				String message = buffer.readLine();
				if(message.charAt(0) == '@') {
					String[] command = message.split(" ");
					switch(command[0]) {
						case "@quit":
							System.exit(0);
							break;
						case "@name":
							name = command[1];
							break;
					}
					continue;
				}
				
				String finalMessage = name + ": " + message;
				
				if(message.length() != 0) {
					byte[] data = new byte[PACKET_SIZE];
					data = finalMessage.getBytes();
					
					DatagramPacket dataPacket = new DatagramPacket(data, data.length, ipAddress, port);				
					currentSocket.send(dataPacket);
				}
				
				
			}
			catch(Exception excep) {
				System.out.println(excep.getMessage());
				System.out.println(excep.getStackTrace());
			}
		}
	}
}
