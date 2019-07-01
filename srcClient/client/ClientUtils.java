package client;

import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.net.URLEncoder;

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
		ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor();
		se.scheduleWithFixedDelay(c, 0, 500, TimeUnit.MILLISECONDS);
	}
	
	public void enviarMensagem(Messages send) throws Exception {
		String sendString = "ENVIANDO#"+URLEncoder.encode(send.getStatus(), "UTF-8")+"#"+URLEncoder.encode(send.getOrigem(), "UTF-8")+"#"+URLEncoder.encode(send.getDestino(), "UTF-8")+"#"+URLEncoder.encode(send.getData(), "UTF-8")+"*"+URLEncoder.encode(send.getDestino(), "UTF-8");
		sendList.add(sendString);
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
				
				
				
			
			
		}
		return null;
	}
	
	public void logout() {
		sendList.add("deslogar*deslogar");
	}
	
	private void confirmarLeitura(int id, String destino) {
		// TODO Auto-generated method stub

	}
	
	
	
}
