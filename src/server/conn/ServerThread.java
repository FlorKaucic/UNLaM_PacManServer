package server.conn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerThread extends Thread {
	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public ServerThread(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;

			out.println("CONNECTED");

			while ((inputLine = in.readLine()) != null) {
				outputLine = ServerProtocol.processInput(inputLine);
				out.println(outputLine);
			}
			in.close();
			clientSocket.close();
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "No se puede comunicar con el cliente", "Servidor", JOptionPane.ERROR_MESSAGE);
			System.out.println("No se puede comunicar con el cliente.");
		}
	}
	
	// CHANGE
//	public PrintWriter getWriter(){
//		return this.out;
//	}
}
