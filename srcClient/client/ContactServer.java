package client;
import util.Messages;
import java.lang.Thread;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	LinkedList<String> send, recieve;
	
	public ContactServer(LinkedList<String> lSend, LinkedList<String> lRecieve) {
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
		send = lSend;
		recieve = lRecieve;
		
	}
	
	public synchronized void enviarMensagem(DataOutputStream os) throws Exception {
		String s;

		try{
			
			s = (String)send.pop();
			
			System.out.println(s);
			
			os.writeUTF(s);
			os.flush();
			
		}catch(Exception e) {
			
		}
		
		
		

	}
	
	public synchronized void receberMensagem(DataInputStream is) throws IOException {
		// TODO fazer metodo
		if(is.available()>0) {
			String message = is.readUTF();
			recieve.add(message);
		}		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
			try {
				//-----------------------inicio do try---------------------------------------
				OutputStream os = server.getOutputStream();
				InputStream is = server.getInputStream();
				DataOutputStream bos = new DataOutputStream(os);
				DataInputStream bis = new DataInputStream(is);
				
				
				
				
				
				
				enviarMensagem(bos);
				receberMensagem(bis);
				
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
