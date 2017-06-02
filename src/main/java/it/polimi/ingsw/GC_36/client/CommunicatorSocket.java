package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicatorSocket implements Communicator {

	Scanner scIn;
	PrintWriter out;

	@Override
	public void connect() throws IOException {

		Socket socket = new Socket(Commons.HOST, Commons.PORT);
		System.out.println("Connection established");

		new Thread(new ServerReader(socket)).start();
		new Thread(new ServerWriter(socket)).start();
	}
}

class ServerReader implements Runnable {
	InputStream socketInput;

	public ServerReader(Socket s) {
		try {
			socketInput = s.getInputStream();
		} catch (IOException e) {
			//TODO impl
			e.printStackTrace();
		}
	}

	public void run() {
		int byteIn;
		while (true) {

			try {
				byteIn = socketInput.read();
				System.out.print((char) byteIn);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}

class ServerWriter implements Runnable {
	PrintWriter socketOut;
	Scanner stdin;

	public ServerWriter(Socket s) {
		try {
			socketOut = new PrintWriter(s.getOutputStream(),
					true);
		} catch (IOException e) {
			//TODO impl
			e.printStackTrace();
		}
		stdin = new Scanner(System.in);

	}

	public void run() {
		String inputLine;
		while (true) {
			inputLine = stdin.nextLine();
			socketOut.println(inputLine);
		}
	}
}