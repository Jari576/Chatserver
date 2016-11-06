package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AdminThread implements Runnable {
	private BufferedReader in;
	private PrintWriter filewriter;
	private File file;

	public AdminThread(File f) {
		this.file = f;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			String input;
			Thread.sleep(500);
			while (true) {
				input = in.readLine();

				if (input != null) {
					if (input.charAt(0) == '!') {
						adminfunctionality(input);
					} else {
						for (ClientThread thread : Server.Clients) {
							thread.getWriter().println("Server : " + input);
						}
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void adminfunctionality(String input) throws IOException, InterruptedException {
		if(input.equals("!getaccounts")) {
			getAllAccounts();
		}
		//kick functionality
		if(input.substring(0, 5).equals("!kick")) {
			String tobekicked = input.substring(6, input.length());
			kick(tobekicked);
		}
		//remove an account
		if(input.substring(0, 7).equals("!remove")) {
			removeaccount(input);
		}
	}
	
	public void kick(String name) throws IOException {
		
		for (ClientThread thread : Server.Clients) {
			thread.getWriter().println(thread.getID() + " got kicked");
			if(thread.getID().equals(name)){
				thread.getWriter().println("ur kicked fgt");
				thread.getSocket().close();
			}
		}
	}
	
	public void removeaccount(String input) throws IOException, InterruptedException {
		filewriter = new PrintWriter(file);
		String toberemoved = input.substring(8, input.length());
		kick(toberemoved);
		for (int i = 0; i < Server.ClientIDs.size(); i++) {
			if (Server.ClientIDs.get(i).equals(toberemoved)) {
				Server.ClientIDs.remove(i);
				Server.Passwords.remove(i);
			}
		}		
		Thread.sleep(500);
		String accounts = "";
		for(int i =0; i < Server.ClientIDs.size(); i++) {
			accounts += Server.ClientIDs.get(i);
			accounts += "\n";
			accounts += Server.Passwords.get(i);
			accounts += "\n";
		}
		
		filewriter.println(accounts);
		filewriter.flush();
	}
	
	public void getAllAccounts(){
		for(String s : Server.ClientIDs) {
			System.out.println(s);
		}
	}
}
