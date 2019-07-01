package client;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;

import util.Messages;

public class MainTeste {
	public static void main(String[] args) {
		ClientUtils utils = new ClientUtils();
		Messages send = new Messages(0, "bengamole####");
		send.setHora(LocalTime.now());
		send.setDestino("0");
		send.setOrigem("0");
		send.setStatus("");
		
		Messages send2 = new Messages(1, "bengaDura####");
		send2.setHora(LocalTime.now());
		send2.setDestino("0");
		send2.setOrigem("0");
		send2.setStatus("");
		
		
		
		try {
			utils.enviarMensagem(send);
			utils.enviarMensagem(send2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		utils.logout();
		for(int i = 0; i<1000000; i++) {
			Messages m=null;
			try {
				m = utils.receberMensagem();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(m!=null)
			System.out.println(m.getData());
		}
	}
	
	
}
