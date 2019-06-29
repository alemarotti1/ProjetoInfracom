package client;
import util.Messages;
import java.lang.Thread;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;
import java.util.LinkedList;


public class ContactServer extends Thread{
	Socket server;
	byte[] sendData, receivedData;
	LinkedList send, recieve;
	
	public ContactServer(LinkedList lSend, LinkedList lRecieve) {
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
		send = lSend;
		recieve = lRecieve;
		
	}
	
	private void enviarMensagem(OutputStream os) throws Exception {
		
		String s = (String)send.pop();
		byte[] saida = s.getBytes();
		
		os.write(saida);
		os.flush();
	}
	
	private void receberMensagem(InputStream is) throws IOException {
		// TODO fazer metodo
		
		is.read(receivedData);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				//-----------------------inicio do try---------------------------------------
				OutputStream os = server.getOutputStream();
				InputStream is = server.getInputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				BufferedInputStream bis = new BufferedInputStream(is);
				
				enviarMensagem(os);
				receberMensagem(is);
				
				URLEncoder.encode("", "UTF-8");
				URLDecoder.decode("", "UTF-8");
				
				Thread.sleep(300);
				
				
				
				//-----------------------fim do try--------------------------------
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}
