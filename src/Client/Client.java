package Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	private static Socket socket;
	private static BufferedReader user_in;
	private static BufferedReader server_in;
	private static PrintWriter out; 
	private static int port;
	
	public static void main(String[] args) {
		socket = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("port:");
		try{
			port = sc.nextInt();
		} catch(InputMismatchException e) {
			System.err.println("incorrect port input");
			System.exit(1);
		}
		System.out.println("1. login");
		System.out.println("2. new account");
		sc.nextLine();
		String login = sc.nextLine();
		while(!(login.equals("1") || login.equals("2"))) {
			System.out.print("1 or 2");
			login = sc.nextLine();
		}
		System.out.println("username:");
		String id = sc.nextLine();
		System.out.println("password:");
		String pw = sc.nextLine();
		
		try {
			socket = new Socket("localhost", port);

			out = new PrintWriter(socket.getOutputStream(), true);
			server_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			user_in = new BufferedReader(new InputStreamReader(System.in));
			String input;
			out.println(login);
			out.println(pw);
			out.println(id);
			while (!socket.isClosed()) {
				
				if (server_in.ready()) {
					input = server_in.readLine();
					if (input != null) {
						System.out.println(input);
						if(input.equals("ur kicked fgt")) {
							socket.close();
						}
					}
				}
				
				if (user_in.ready()) {
					input = user_in.readLine();
					out.println(input);
				}
			}
			System.err.println("lost connection to the server");
		sc.close();
		} catch (IOException e) {
			System.err.println("Couldn't connect to the server");
			System.exit(1);
		} catch(IllegalArgumentException e) {
			System.err.println("incorrect port input");
			System.exit(1);
		}
	}
}