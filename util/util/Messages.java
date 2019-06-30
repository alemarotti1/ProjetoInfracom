package util;

public class Messages {
	private int id;
	private String data;
	private String status, origem, destino;

// The field status can contain one of the following codes:
// "RECEIVED" - The server received the message
// "DELIVERED" - The server successfully delivered the message to the other client
// "READ" - The message was read by the other user

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Messages(int id, String data) {
		this.setId(id);
		this.setData(data);
		this.initializeStatus();
	}

	public Messages(Messages m) {
		this.setId(m.id);
		this.setData(m.data);
		this.receive();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	private void initializeStatus() {
		this.status = "";
	}

	public void receive() {
		this.status = "RECEIVED";
	}

	public void read() {
		this.status = "READ";
	}

	public void ack() {
		this.status = "ACK";
	}
}