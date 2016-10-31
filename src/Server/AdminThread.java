package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AdminThread implements Runnable {
	private BufferedReader in;
	private PrintWriter filewriter;
	// private File file;

	public AdminThread() {

	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			// filewriter = new PrintWriter(new FileWriter(file, true));
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
	
	public void adminfunctionality(String input) throws IOException {
		//kick functionality
		if(input.substring(0, 5).equals("!kick")) {
			kick(input);
		}
		
	}
	
	public void kick(String input) throws IOException {
		String tobekicked = input.substring(6, input.length());
		for (ClientThread thread : Server.Clients) {
			if(thread.getID().equals(tobekicked)){
				thread.getWriter().println("ur kicked fgt");
				thread.getSocket().close();
			}
		}
	}
}
