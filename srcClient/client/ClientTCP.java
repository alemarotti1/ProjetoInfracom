package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
	
	public static void main(String[] args) throws IOException {
		System.out.println("envie \"fim\" para terminar");
		Scanner in = new Scanner(System.in);
		int port = 5093;
		InetAddress IPserver = InetAddress.getByName("localhost");
		byte[] sendData, receivedData = new byte[100];
		Socket server = new Socket(IPserver, port);
		
		
		while (true) {
			String s = in.nextLine();
			System.out.println("String enviada: "+s);
			sendData = s.getBytes();
			
			OutputStream saida = server.getOutputStream();
			long tempo2, tempo1 = System.nanoTime();
	        saida.write(sendData);
			
			InputStream is = server.getInputStream();
			is.read(receivedData);
			tempo2=System.nanoTime();
			String received = new String(data(receivedData));
			System.out.println(received);
			System.out.println("RTT = "+ (tempo2-tempo1) + "nano segundos");
	        
			
			receivedData = new byte[100];
			sendData = new byte[1];
			if (s.contains("fim")) {
				break;
			}
		}
	}
	public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    }
}
