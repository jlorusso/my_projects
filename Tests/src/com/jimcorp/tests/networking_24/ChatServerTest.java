package com.jimcorp.tests.networking_24;

import javax.swing.JFrame;

public class ChatServerTest {

	public static void main(String[] args) {
		
		ChatServer server = new ChatServer();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.runServer();
	}

}
