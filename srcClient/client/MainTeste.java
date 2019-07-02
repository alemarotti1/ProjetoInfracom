package client;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import util.Messages;

public class MainTeste extends Thread{
	static ClientUtils utils;
	static Scanner scanner = new Scanner(System.in);;
	static int id=0;
	static String meuNumero = "0";
	static String numeroDestino = "1";
	static ArrayList<Messages> messagesBuffer = new ArrayList<Messages>();
	
	public MainTeste(ClientUtils u) {
		utils = u;
		
		System.out.println("Digite seu numero");
		MainTeste.meuNumero = scanner.nextLine();
		
		System.out.println("Digite numero destino");
		MainTeste.numeroDestino = scanner.nextLine();
		
	}
	
	
	@Override
	public void run() {
		try {
			
			
		
			Messages receive = utils.receberMensagem();
			if(receive != null) {
				if((receive.getTipo()).equalsIgnoreCase("RECEBIDO")||(receive.getTipo()).equalsIgnoreCase("LIDO")) {
					for (Messages m : messagesBuffer)  {
						if(m.getOrigem() == meuNumero) {
							if(m.getId() == receive.getId()) {
								// Marca mensagem como recebida ou como lida. Se ja estava como recebida, marca como lida.
								// Senao, marca como recebida.
								if(m.getStatus().equalsIgnoreCase("RECEIVED")) {
									m.read();
								} else {
									m.receive();
								}
							}
								
						} 
			        }
				}else if((receive.getTipo()).equalsIgnoreCase("ACK")) {
					for (Messages m : messagesBuffer)  {
						if(m.getOrigem() == meuNumero) {
							// Marca mensagem como recebida pelo servidor
							if(m.getStatus().equalsIgnoreCase("SENT")) {
								m.ack();
							}
						} 
			        }
				}
				else {
					messagesBuffer.add(receive);
					
					Messages response = new Messages(receive.getId(), receive.getData());
					response.setDestino(receive.getOrigem());
					response.setOrigem(meuNumero);
					response.setStatus("RECEIVED");
					response.setTipo("RECEBIDO");
					
					utils.enviarMensagem(response);
					//System.out.println("Seu amigo diz: " + receive.getData());
				}
		
				printCurrentMessageBuffer();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		ClientUtils temp = new ClientUtils();
		
		
		ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor();
		se.scheduleWithFixedDelay(new MainTeste(temp), 0, 500, TimeUnit.MILLISECONDS);
		
		while(true) {
			String mensagem = scanner.nextLine();
			
			// Antes de receber a próxima mensagem, itera sobre as mensagens atuais vendo quais estao como recebidas,
			// e envia confirmação de leitura
			for (Messages m : messagesBuffer)  {
				if(m.getStatus().equalsIgnoreCase("RECEIVED") && m.getOrigem() != meuNumero) {
					m.read();
					Messages readConf = new Messages(m.getId(), m.getData());
					readConf.setDestino(m.getOrigem());
					readConf.setOrigem(meuNumero);
					readConf.setStatus("READ");
					readConf.setTipo("LIDO");
					
					utils.enviarMensagem(readConf);
				}
	        }
			
			Messages mens = new Messages(MainTeste.id, mensagem);
			
			
			
			if(mens != null) {
				MainTeste.id++;
				mens.setDestino(numeroDestino);
				mens.setHora(LocalTime.now());
				mens.setOrigem(meuNumero);
				mens.setStatus("SENT");
				mens.setTipo("ENVIADO");
				utils.enviarMensagem(mens);
				
				messagesBuffer.add(mens);
				printCurrentMessageBuffer();
			}
			
		}
		
	}
	
	public static void printCurrentMessageBuffer() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		for (Messages m : messagesBuffer)  {
			if(m.getOrigem() == meuNumero) {
				System.out.println("Eu: " + " => " + m.getData() + " -- " + m.getStatus());
			} else {				
				System.out.println("Amigo: " + " => " + m.getData() + " -- " + m.getStatus());
			}
        }
	}
	
	
}
