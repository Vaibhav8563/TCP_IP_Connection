package tcpServer;



	import java.io.*;
	import java.net.*;

	public class tcpTest {
		public static void main(String[] args) {
	        int port = 5000;

	        try (ServerSocket serverSocket = new ServerSocket(port)) {
	            System.out.println("Server is listening on port " + port);

	            Socket socket = serverSocket.accept();
	            System.out.println("Client connected");

	            // Using DataInputStream and DataOutputStream
	            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
	            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

	            // Communication loop
	            String clientMessage = "";
	            String serverMessage = "";
	            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

	            while (true) {
	                // Read message from client
	                clientMessage = dataInputStream.readUTF();
	                System.out.println("Client: " + clientMessage);

	                // Check exit condition
	                if (clientMessage.equalsIgnoreCase("exit")) {
	                    System.out.println("Client has disconnected. Closing connection...");
	                    break;
	                }

	                // Server sends a message
	                System.out.print("Server (type 'exit' to disconnect): ");
	                serverMessage = serverInput.readLine();
	                dataOutputStream.writeUTF(serverMessage);
	                dataOutputStream.flush();

	                // Check exit condition
	                if (serverMessage.equalsIgnoreCase("exit")) {
	                    System.out.println("Server exiting. Closing connection...");
	                    break;
	                }
	            }

	            // Close resources
	            dataInputStream.close();
	            dataOutputStream.close();
	            socket.close();
	            System.out.println("Connection closed");

	        } catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	}
