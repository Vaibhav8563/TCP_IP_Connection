package tcpClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpClient {
	public static void main(String[] args) {
		String hostname = "localhost"; // Server address
		int port = 5000; // Server port

		try (Socket socket = new Socket(hostname, port)) {
			// Input and output streams for communication
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// Scanner to read user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to send to the server: ");
            String userMessage = scanner.nextLine();
 
			// Send a message to the server
			writer.println(userMessage);

			// Read server response
			String serverMessage = reader.readLine();
			System.out.println("Server says: " + serverMessage);
			
			// Close the scanner
            scanner.close();

		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
		}
	}
}



/*

1. OutputStreamWriter:
			Converts characters into bytes and writes them to an output stream.
			It's often wrapped with BufferedWriter or PrintWriter for additional functionalities like line buffering.

2. PrintWriter:
			A PrintWriter is a higher-level writer that provides methods like println(), print(), and format().
			It's used to send formatted text data easily.

3. InputStream:
			A Java class used to read bytes from various sources (files, network sockets).
			Reads bytes from the client and converts them into a string.

4. BufferedReader:
			A BufferedReader reads text from an input stream (like socket input) efficiently by buffering the characters.
			It allows reading lines of text using readLine().
			
*/