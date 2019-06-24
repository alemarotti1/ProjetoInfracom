package gui;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.Scanner;

import client.ContactServer;

public class MainGui {

	public static void main(String[] args) {
		LinkedList<String> l = new LinkedList<String>();
		ContactServer c = new ContactServer(l);
		Thread t = new Thread(c);
		t.start();
		
		
		Scanner i = new Scanner(System.in);
		while(true) {
			System.out.println("1 = add 0=break");
			int a = i.nextInt();
			if(a==1)
				l.add("aaa");
			else
				break;
		} 
		

	}

}
