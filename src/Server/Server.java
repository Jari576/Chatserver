package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	static ServerSocket server;
	static ArrayList<ClientThread> Clients;
	static ArrayList<String> ClientIDs;
	static ArrayList<String> Passwords;
	static int port;
	static final File file = new File("src\\Server\\Accounts.txt");

	public static void main(String[] args) {
		ClientIDs = new ArrayList<String>();
		Passwords = new ArrayList<String>();
		ImportAccounts(file);
		try {
			server = new ServerSocket(0);
			port = server.getLocalPort();
			System.out.println("Server started");
			System.out.println("Port: " + port);
			System.out.println("waiting for connection...");
			Thread at = new Thread(new AdminThread());
			at.start();
			AcceptClients();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void AcceptClients() throws IOException {
		Clients = new ArrayList<ClientThread>();
		while (true) {
			try {
				Socket socket = server.accept();
				System.out.println("incomming connection");
				ClientThread client = new ClientThread(socket, file);
				Thread t = new Thread(client);
				Clients.add(client);
				t.start();
			} catch (IOException e) {

			}
		}
	}

	public static void ImportAccounts(File file){
		Scanner s; 
		try{
		s = new Scanner(file);
		while(s.hasNextLine()) {
			ClientIDs.add(s.nextLine());
			Passwords.add(s.nextLine());
		}
		s.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
