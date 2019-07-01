package client;

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
		try {
			utils.enviarMensagem(send);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
