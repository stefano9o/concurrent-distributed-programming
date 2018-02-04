package cdp.exams.may;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SyncServer implements Runnable{
	private ServerSocket listener;
	int port;
	
	public SyncServer(int port) {
		this.port = port;
	}
	
	public void startConnection() throws IOException{
		System.out.println("hi here is " + Thread.currentThread().getName());
		System.out.println("connecting socket " + port);
		listener = new ServerSocket(port);
		try {
	        while (true) {
	        	System.out.println("waiting for a client");
	        	Socket socket = listener.accept();
	        	System.out.println("connected with a client");
	        	try {
	        		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        		out.println("Hi client, here server is speaking ...");
	        	} finally {
	        		System.out.println("closing socket");
	        		socket.close();
	        	}
	        }
	    }
	    finally {
	        listener.close();
	    }	
	}

	@Override
	public void run() {
		try {
			this.startConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] argv) {
		int port = 5550;
		if(argv.length > 0) {
			port = Integer.parseInt(argv[0]);
		}

		(new Thread(new SyncServer(port))).start();
	}
}