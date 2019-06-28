package client;
import util.Messages;
import java.lang.Thread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class ContactServer extends Thread{
	Socket server;
	byte[] sendData, receivedData;
	LinkedList l;
	
	public ContactServer(LinkedList lTemp) {
		/*int port = 5093;
		InetAddress IPserver;
		try {
			IPserver = InetAddress.getByName("localhost");
			receivedData = new byte[100];
			server = new Socket(IPserver, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		l = lTemp;
		
	}
	
	private void enviarMensagem(OutputStream os) throws Exception {
		
		String s = (String)l.pop();
		byte[] saida = s.getBytes();
		
		os.write(saida);
		os.flush();
	}
	
	private void receberMensagem(LinkedList<Messages> message) {
		// TODO fazer metodo
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			OutputStream os = server.getOutputStream();
			if(!l.isEmpty()) {
				System.out.println("not empty");
				l.pop();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}
