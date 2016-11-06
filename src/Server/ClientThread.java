package Server;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String id = null;
	private String password = null;
	private PrintWriter filewriter;
	private File file;

	public ClientThread(Socket s, File f) {
		this.socket = s;
		this.file = f;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			filewriter = new PrintWriter(new FileWriter(file, true));
			String input;
			Thread.sleep(500);
			out.println("welcome to my server");
			
			login();
			
			System.out.println(id + " connected");
			for (ClientThread thread : Server.Clients) {
				if (thread != this) {
					thread.getWriter().println(id + " connected");
				}
			}

			while (!socket.isClosed()) {
				input = in.readLine();
				if (input != null) {
					System.out.println(id + ": " + input);
					for (ClientThread thread : Server.Clients) {
						if (thread != this) {
							thread.getWriter().println(id + ": " + input);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(id + " disconnected");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void login() throws IOException {
		
		String login = in.readLine();
		id = in.readLine();
		password = in.readLine();
		
		while (!(login.equals("1") || login.equals("2"))) {
			System.out.println("1 or 2");
			login = in.readLine();
		} 
	
		switch(login) {
		case "1" : 
			existingAccount();
			break;
		case "2" : 
			newAccount();
			break;
		}
	}
	
	public void newAccount() throws IOException {
		while (Server.ClientIDs.contains(id)) {
			out.println("Username already in use.");
			out.println("Username:");
			id = in.readLine();
			out.println("Password:");
			password = in.readLine();
		}
		Server.ClientIDs.add(id);
		Server.Passwords.add(password);
		filewriter.println(id);
		filewriter.println(password);
		filewriter.close();
	}
	
	public void existingAccount() throws IOException {
		boolean correctcredentials = false;
		while (!correctcredentials) {

			for (int i = 0; i < Server.ClientIDs.size(); i++) {
				if (Server.ClientIDs.get(i).equals(id)) {
					if (Server.Passwords.get(i).equals(password)) {
						correctcredentials = true;
					}
				}
			}
			if (!correctcredentials) {
				out.println("username or password incorrect, try again.");
				out.println("Username:");
				id = in.readLine();
				out.println("Password:");
				password = in.readLine();
			}
		}
	}
	public Socket getSocket() {
		return socket;
	}

	public String getID() {
		return id;
	}
	
	public PrintWriter getWriter() {
		return out;
	}
}