package tcpClient;



	import java.io.*;
	import java.net.*;
	import java.util.Scanner;

	public class ClientTest {
			    public static void main(String[] args) {
	        String hostname = "localhost";
	        int port = 5000;

	        try (Socket socket = new Socket(hostname, port)) {
	            // Using DataInputStream and DataOutputStream
	            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
	            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

	            // Scanner for user input
	            Scanner scanner = new Scanner(System.in);
	            String clientMessage = "";
	            String serverMessage = "";

	            // Communication loop
	            while (true) {
	                // Client sends a message
	                System.out.print("Client (type 'exit' to disconnect): ");
	                clientMessage = scanner.nextLine();
	                dataOutputStream.writeUTF(clientMessage);
	                dataOutputStream.flush();

	                // Check exit condition
	                if (clientMessage.equalsIgnoreCase("exit")) {
	                    System.out.println("Client exiting. Closing connection...");
	                    break;
	                }

	                // Read server message
	                serverMessage = dataInputStream.readUTF();
	                System.out.println("Server: " + serverMessage);

	                // Check exit condition
	                if (serverMessage.equalsIgnoreCase("exit")) {
	                    System.out.println("Server has disconnected. Closing connection...");
	                    break;
	                }
	            }

	            // Close scanner and resources
	            scanner.close();
	            dataInputStream.close();
	            dataOutputStream.close();
	            System.out.println("Connection closed");

	        } catch (UnknownHostException ex) {
	            System.out.println("Server not found: " + ex.getMessage());
	        } catch (IOException ex) {
	            System.out.println("I/O error: " + ex.getMessage());
	        }
	    }
	}
