package Client;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;



public class ClientFrame {
	protected JFrame frame;
	
	private JTextArea receivedTextArea;
	private JTextArea textAreaUsersConnected;
	
	private JButton btnDisconnect;
	private JButton sendButton;
	
	private JLabel lblHostConnected;
	private JLabel lblUsersConnected;
	private JLabel lblConnectedTo;
	private JLabel lblAddress;
	private JLabel lblPort;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	private JTextField textFieldHostName;
	private JTextField textFieldHostPort;
	private JTextField sendTextField;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	
	private Boolean connected=false;
	

	
	//Main, basically to test application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ClientFrame() {
		initialize();
	}

		private void initialize() {
		//Create Frame
		frame = new JFrame("ChatClient");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Initialize fields
		sendTextField = new JTextField(10);
		sendButton = new JButton("Send");
		textAreaUsersConnected = new JTextArea();
		btnDisconnect = new JButton("Connect");
		textFieldHostPort = new JTextField();
		textFieldHostName = new JTextField();
		lblPort = new JLabel("Port:");
		lblAddress = new JLabel("Address:");
		lblUsername = new JLabel("UserName:");
		lblPassword = new JLabel("Password:");
		receivedTextArea = new JTextArea();
		
		
		//ActionListeners
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				putSendTextFieldInReceivedTextArea();
				sendTextField.setText("");
			}
		});
		sendTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				putSendTextFieldInReceivedTextArea();
				sendTextField.setText("");
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event){
				ExitProgram();
			}
			
		});
		btnDisconnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(connected){
					//Put stuff here to disconnect from a server
					updateLayout(false);
				} else {
					//Put stuff here to connect to a server with address textFieldHostName.getText(), textFieldHostPort.getText()
					updateLayout(true);
				}
				
			}
		});
		
		
		
		//SendButton
		sendButton.setBounds(305, 216, 115, 22);
		sendButton.setEnabled(false);
		sendButton.setVisible(false);
		
		//SendTextField
		sendTextField.setBounds(12, 216, 281, 22);
		sendTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendTextField.setEnabled(false);
		sendTextField.setVisible(false);
		
		//Label "Connected to:"
		lblConnectedTo = new JLabel("Connected to:");
		lblConnectedTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConnectedTo.setBounds(305, 13, 115, 22);
		
		//Label with IP connected to
		lblHostConnected = new JLabel("Not Connected");
		lblHostConnected.setForeground(Color.RED);
		lblHostConnected.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHostConnected.setBounds(315, 34, 115, 22);
		
		//Label "Users connected:"
		lblUsersConnected = new JLabel("Users connected:");
		lblUsersConnected.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsersConnected.setBounds(305, 57, 125, 16);
		lblUsersConnected.setVisible(false);
		
		//textAreaUsersConnected
		textAreaUsersConnected.setBackground(Color.LIGHT_GRAY);
		textAreaUsersConnected.setBounds(294, 74, 135, 112);
		textAreaUsersConnected.setEditable(false);
		textAreaUsersConnected.setVisible(false);
		
		//btnDisconnect
		btnDisconnect.setBounds(305, 188, 115, 25);
		btnDisconnect.setForeground(Color.BLUE);
		
		//textFieldHostName
		textFieldHostName.setBounds(30, 55, 116, 22);
		textFieldHostName.setColumns(10);
		
		//textFieldHostPort
		textFieldHostPort.setBounds(30, 120, 116, 22);
		textFieldHostPort.setColumns(10);
		
		//lblAddress
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(40, 14, 75, 22);
		lblAddress.setVisible(true);
		
		//lblPort
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPort.setBounds(40, 91, 56, 16);
		lblPort.setVisible(true);
		
		//ReceiverdTextArea
		receivedTextArea.setBounds(12, 13, 281, 190);
		receivedTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		receivedTextArea.setEditable(false);
		receivedTextArea.setVisible(false);
		
		//Password
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(158, 120, 116, 22);
		textFieldPassword.setColumns(10);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(166, 92, 75, 22);
		
		//UserName
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(158, 55, 116, 22);
		textFieldUserName.setColumns(10);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(166, 14, 82, 22);
		
		//Add all the stuff to the frame
		frame.getContentPane().add(sendButton);
		frame.getContentPane().add(sendTextField);
		frame.getContentPane().add(textAreaUsersConnected);
		frame.getContentPane().add(lblUsersConnected);
		frame.getContentPane().add(lblHostConnected);
		frame.getContentPane().add(lblConnectedTo);
		frame.getContentPane().add(btnDisconnect);		
		frame.getContentPane().add(textFieldHostName);
		frame.getContentPane().add(textFieldHostPort);
		frame.getContentPane().add(lblAddress);
		frame.getContentPane().add(lblPort);
		frame.getContentPane().add(textFieldPassword);
		frame.getContentPane().add(lblUsername);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(textFieldUserName);
		frame.getContentPane().add(receivedTextArea);
		
		
		
		
	}

	
	private void doCommand(){
		if(sendTextField.getText().equals("/Command Do Clear")){
			receivedTextArea.setText("");
			return;
		}
		if(sendTextField.getText().startsWith("/Command Do AddUser ")){
			addToUserList(sendTextField.getText().substring(20));
			return;
		}
		if(sendTextField.getText().startsWith("/Command Do RemoveUser ")){
			removeFromUserList(sendTextField.getText().substring(23));
			return;
		}
		
		receivedTextArea.append("[Console] No such command\n");
	}
	
	private void putSendTextFieldInReceivedTextArea(){
		if(sendTextField.getText().toUpperCase().startsWith("/COMMAND DO ")){
			doCommand();
			return;
		}
		receivedTextArea.append(sendTextField.getText()+"\n");
	}
	
	
	
	private void ExitProgram(){
		frame.dispose();
		System.exit(0);
	}
	
	private void addToUserList(String name){
		textAreaUsersConnected.append("- "+name+"\n");
	}
	
	private void removeFromUserList(String name){
		if(textAreaUsersConnected.getText().contains(name)){
			textAreaUsersConnected.setText(textAreaUsersConnected.getText().replaceAll("- "+name+"\n",""));
		}
	}
	
	private void updateLayout(boolean connected){
		if(connected){
			this.connected=true;
			
			lblHostConnected.setText(textFieldHostName.getText()+":"+textFieldHostPort.getText());
			lblHostConnected.setForeground(Color.BLUE);
			
			btnDisconnect.setText("Disconnect");
			btnDisconnect.setForeground(Color.RED);
			
			//Add list of users online
			lblUsersConnected.setVisible(true);
			textAreaUsersConnected.setVisible(true);
			
			//Remove all connection textFields and stuff
			textFieldHostName.setVisible(false);
			textFieldHostPort.setVisible(false);
			lblPort.setVisible(false);
			lblAddress.setVisible(false);
			
			//Remove all login textFields and stuff
			textFieldPassword.setVisible(false);
			textFieldUserName.setVisible(false);
			lblPassword.setVisible(false);
			lblUsername.setVisible(false);
			
			//Enable send stuff
			sendTextField.setVisible(true);
			sendButton.setVisible(true);
			sendTextField.setEnabled(true);
			sendButton.setEnabled(true);
			receivedTextArea.setVisible(true);
		} else {
			this.connected=false;
			
			lblHostConnected.setText("Not Connected");
			lblHostConnected.setForeground(Color.RED);
			
			btnDisconnect.setText("Connect");
			btnDisconnect.setForeground(Color.BLUE);
			
			//Remove user list
			lblUsersConnected.setVisible(false);
			textAreaUsersConnected.setVisible(false);
			
			//Show all connection textFields and labels
			textFieldHostName.setVisible(true);
			textFieldHostPort.setVisible(true);
			lblPort.setVisible(true);
			lblAddress.setVisible(true);
			
			//Remove all login textFields and stuff
			textFieldPassword.setVisible(true);
			textFieldUserName.setVisible(true);
			lblPassword.setVisible(true);
			lblUsername.setVisible(true);
			
			//disable send stuff
			sendTextField.setVisible(false);
			sendButton.setVisible(false);
			sendTextField.setEnabled(false);
			sendButton.setEnabled(false);
			receivedTextArea.setVisible(false);
		}
	}
	
	public int getHostPort(){
		return Integer.parseInt(textFieldHostPort.getText());
	}
	public String getHostName(){
		return textFieldHostName.getText();
	}
}
