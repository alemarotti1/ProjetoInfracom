package client;

import java.util.LinkedList;
import java.util.StringTokenizer;
import java.lang.Thread;
import util.Messages;

public class ClientUtils {
	Thread t;
	LinkedList<String> sendList;
	LinkedList<String> recieveList;
	ContactServer c;
	
	public ClientUtils() {
		// TODO Auto-generated constructor stub
		sendList = new LinkedList<String>();
		recieveList = new LinkedList<String>();
		c = new ContactServer(sendList, recieveList);
		t = new Thread(c);
		t.start();
	}
	
	public void enviarMensagem(Messages send) {
		String sendString = "ENVIANDO#"+send.getStatus()+"#"+send.getOrigem()+"#"+send.getDestino()+"#"+send.getData();
		
	}
	
	
	
	public Messages receberMensagem() {
		if(!recieveList.isEmpty()) {
			String temp = recieveList.pop();
			String[] mensagemRecebida = temp.split("#");
			String tipo, id, status, origem, mensagem="";
			Messages returnVar;
			
				tipo = mensagemRecebida[0];
				id = mensagemRecebida[1];
				origem = mensagemRecebida[2];
				try {
					mensagem = mensagemRecebida[3];
				} catch (Exception e) {
					// TODO: handle exception
				}
				returnVar = new Messages(Integer.parseInt(id), mensagem);
				returnVar.setOrigem(origem);
				if
				
				
			
			
		}
		return null;
	}
	
	private void logout() {
		// TODO Auto-generated method stub

	}
	
	private void confirmarLeitura(int id, String destino) {
		// TODO Auto-generated method stub

	}
	
	
	
}
