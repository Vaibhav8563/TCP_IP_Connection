package tcpServer;

import java.io.*;
import java.net.*;

public class TcpServer {
	public static void main(String[] args) {
		int port = 5000; // Server port

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);

			// Wait for a client to connect
			Socket socket = serverSocket.accept();
			System.out.println("Client connected");

			// Input and output streams to communicate with the client
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			// Read message from the client
			String clientMessage = reader.readLine();
			System.out.println("Client says: " + clientMessage);

			// Respond to the client
			writer.println("Hello Client, your message was received!");

			// Close the socket
			socket.close();
			System.out.println("Connection closed");

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}