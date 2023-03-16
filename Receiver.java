package lab_udp_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver extends Thread {
	DatagramSocket currentSocket;
	private static final int PACKET_SIZE = 1024;
	
	Receiver(DatagramSocket socket){
		try {
			if (socket.isClosed()) {
	            throw new Exception("Cannot open recieving socket");
	        }
			currentSocket = socket;
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
		}
	}
	
	public void run() {
		while(true) {
			try {
				byte[] data = new byte[PACKET_SIZE];
				DatagramPacket dataPacket = new DatagramPacket(data, data.length);
				currentSocket.receive(dataPacket);
				String message = new String(dataPacket.getData());
				System.out.println('\n' + "received such a message--->" + message);
				
			}
			catch(IOException exep) {
				System.out.println(exep.getMessage());
			}
		}
	}
}