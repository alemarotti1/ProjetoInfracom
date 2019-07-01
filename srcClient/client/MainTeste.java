package client;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import util.Messages;

public class MainTeste extends Thread{
	ClientUtils utils;
	Scanner scanner;
	static int id=0;
	String numeroContato;
	
	public MainTeste(ClientUtils u) {
		scanner = new Scanner(System.in);
		utils = u;
		
		System.out.println("Digite seu numero para contato");
		numeroContato = scanner.nextLine();
		
	}
	
	
	@Override
	public void run() {
		Messages tmp = typeMensagem();
		try {
			if(tmp!=null)
			utils.enviarMensagem(tmp);
		
			Messages recieve = utils.receberMensagem();
			if(recieve!=null) {
				System.err.println(recieve.getOrigem()+"  -  "+recieve.getData());
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public Messages typeMensagem() {
		System.out.println("deseja enviar uma mensagem? Y/N");
		String enviar = scanner.nextLine();
		
		if(enviar.contains("Y")) {
			System.out.println("digite seu destinatário");
			int destino = Integer.parseInt(scanner.nextLine());
			
			System.out.println("digite sua mensaagem");
			String mensagem = scanner.nextLine();
			
			Messages mens = new Messages(MainTeste.id, mensagem);
			MainTeste.id++;
			mens.setDestino(Integer.toString(destino));
			mens.setHora(LocalTime.now());
			mens.setOrigem(numeroContato);
			mens.setStatus("SENT");
			
			
			return mens;
		}
		return null;
		
	}
	
	
	public static void main(String[] args) {
		ClientUtils temp = new ClientUtils();
		
		
		ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor();
		se.scheduleWithFixedDelay(new MainTeste(temp), 0, 500, TimeUnit.MILLISECONDS);
		
	}
	
	
}
