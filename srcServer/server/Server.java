package server;

//Java implementation of  Server side 
//It contains two classes : Server and ClientHandler 
//Save file as Server.java 

import java.io.*; 
import java.util.*; 
import java.net.*; 

//Server class 
public class Server  
{ 

 // Vector to store active clients 
 static Vector<ClientHandler> ar = new Vector<>(); 
   
 // counter for clients 
 static int i = 0; 

 public static void main(String[] args) throws IOException  
 { 
     // server is listening on port 5093 
     ServerSocket ss = new ServerSocket(5093); 
       
     Socket s; 
       
     // running infinite loop for getting 
     // client request 
     while (true)  
     { 
         // Accept the incoming request 
         s = ss.accept(); 

         System.out.println("New client request received : " + s); 
           
         // obtain input and output streams 
         DataInputStream dis = new DataInputStream(s.getInputStream()); 
         DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
           
         System.out.println("Creating a new handler for this client..."); 

         // Create a new handler object for handling this request. 
         ClientHandler mtch = new ClientHandler(s, Integer.toOctalString(i), dis, dos); 

         // Create a new Thread with this object. 
         Thread t = new Thread(mtch); 
           
         System.out.println("Adding this client to active client list"); 

         // add this client to active clients list 
         ar.add(mtch); 

         // start the thread. 
         t.start(); 

         // increment i for new client. 
         // i is used for naming only, and can be replaced 
         // by any naming scheme 
         i++; 

     } 
 } 
} 

//ClientHandler class 
class ClientHandler implements Runnable  
{ 
 Scanner scn = new Scanner(System.in); 
 private String name; 
 final DataInputStream dis; 
 final DataOutputStream dos; 
 Socket s; 
 boolean isloggedin; 
   
 // constructor 
 public ClientHandler(Socket s, String name, 
                         DataInputStream dis, DataOutputStream dos) { 
     this.dis = dis; 
     this.dos = dos; 
     this.name = name; 
     this.s = s; 
     this.isloggedin=true; 
 } 

 @Override
 public void run() { 

     String received; 
     while (true)  
     { 
         try
         { 
             // receive the string 
             received = dis.readUTF();
               
             // break the string into message and recipient part 
             StringTokenizer st = new StringTokenizer(received, "*"); 
             //TIPO STATUS ID  ORIGEM  MENSAGEM DESTINATARIO
             String MsgToSend = st.nextToken(); //mensagem para mandar
             System.out.println(MsgToSend);
             String recipient = st.nextToken(); //para quem mandar
             
             //Antes de fazer qualquer coisa, envia a quem mandou a mensagem confirmação de recebimento:
             this.dos.writeUTF("ACK#RECEIVEDBYSERVER#0#0#0#NOMSG");
             
             if(recipient.equals("deslogar")){ 
                 this.isloggedin=false; 
                 this.s.close(); 
                 break; 
             } else if(recipient.equals("Lista de contatos")){ 
            	 for (ClientHandler mc : Server.ar)  
                 { 
                     // checa se o cliente da lista esta logado, se estiver envia seu nome a quem pediu
                     if (mc.isloggedin==true)  
                     { //por enquanto ta enviando errado, tem que enviar pra quem pediu
                    	 
                         mc.dos.writeUTF(MsgToSend); 
                          
                     } 
                 } 
             }

             // search for the recipient in the connected devices list. 
             // ar is the vector storing client of active users 
             for (ClientHandler mc : Server.ar)  
             { 
                 // if the recipient is found, write on its 
                 // output stream 
                 if (mc.name.equals(recipient)/* && mc.isloggedin==true*/)  
                 { 
                	 //System.out.println(mc.name);
                     mc.dos.writeUTF(MsgToSend); 
                     break; 
                 } 
             } 
         } catch (IOException e) { 
               
             e.printStackTrace(); 
         } 
           
     } 
     try
     { 
         // closing resources 
         this.dis.close(); 
         this.dos.close(); 
           
     }catch(IOException e){ 
         e.printStackTrace(); 
     } 
 } 
}