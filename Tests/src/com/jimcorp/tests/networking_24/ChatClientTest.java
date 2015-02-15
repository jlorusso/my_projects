package com.jimcorp.tests.networking_24;

import javax.swing.JFrame;

public class ChatClientTest {

	public static void main(String[] args) {
		
		ChatClient client = new ChatClient("127.0.0.1");
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.runClient();
	}

}
