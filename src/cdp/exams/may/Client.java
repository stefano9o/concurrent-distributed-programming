package cdp.exams.may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{
	Socket s;
	int port;
	
	public Client(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			this.connection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void connection() throws IOException {
        String serverAddress = "127.0.0.1";
        System.out.println("connecting with socket to port " + port);
        s = new Socket(serverAddress, port);
        
        System.out.println("reading message from server");
        BufferedReader input =
        		new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        
        System.out.println("read the following message from server");
        System.out.println(answer);
        System.exit(0);
	}
	
	public static void main (String[] argv) {
		int port = 5550;
		if(argv.length > 0) {
			port = Integer.parseInt(argv[0]);
		}
		
		(new Thread(new Client(port))).start();
	}
	
}
