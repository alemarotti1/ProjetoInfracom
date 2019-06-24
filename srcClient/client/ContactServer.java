package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ContactServer {
	Socket server;
	byte[] sendData, receivedData;
	
	public ContactServer() {
		int port = 5093;
		InetAddress IPserver;
		try {
			IPserver = InetAddress.getByName("localhost");
			receivedData = new byte[100];
			server = new Socket(IPserver, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void startConnection() {
		//TODO fazer metodo
	}
	private void enviarMensagem() {
		// TODO fazer metodo

	}
	
	private void receberMensagem() {
		// TODO fazer metodo

	}
	
	
}
