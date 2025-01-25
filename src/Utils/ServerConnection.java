package Utils;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.json.JSONException;


public class ServerConnection {
    
    private Socket socket;
    private DataInputStream  in;
    private PrintStream out;
    private static ServerConnection instance;
    
    private Navigation nav = new Navigation();
    
    private ServerConnection(){
    }
    
     public static ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }
    
    public boolean checkServerAvailibily(String serverIP){
//        try {
//            socket = new Socket(serverIP,5005);
//            socket.close();
//            return true;
//        } catch (IOException ex) {
//           return false;
//        }
          return !socket.isClosed();
    }
    
    
    public void openConnection() throws IOException{
        String serverIP = SharedData.getInstance().getServerIp();
        socket = new Socket(serverIP,5005);
        in = new DataInputStream(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
        handlerServer();
    }
    

    private void handlerServer() {
        Thread th=new Thread(() -> {
        try {
            String message;
            while (socket!=null &&!socket.isClosed()&& instance.checkServerAvailibily(SharedData.getInstance().getServerIp())) {
                System.out.println("wait to lisent.....");
                message = in.readLine();
                System.out.println("respooooonse"+message.toString());

                if (message == null) {
                    System.out.println("Received null message from server. Continuing to listen...");
                    continue;
                }
                ServerMessagesRouter.routeServerMessage(message);
                if(SharedData.getInstance().getPlayerID()== 0) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Logged out");
        } catch (JSONException e) {
            System.err.println("Error parsing JSON message from server: ");
        } 
    });
        th.setDaemon(true);
        th.start();
    } 
    
    
    public void sendRequest(String request) throws IOException {
        if(instance.checkServerAvailibily(SharedData.getInstance().getServerIp())){
            out.println(request);
            out.flush();
        }else{
            nav.ShowAlerts("ErrorAlert");
        }
    }

    
   /* public String reciveRequset() throws IOException{
        System.out.println("try to recive request");
        String response=in.readLine();
        System.out.println("After try");
        return response;
    }*/
    public String reciveRequset() throws IOException {
    try {
        System.out.println("Waiting to read line...");
        String line = in.readLine(); // Read a line from the server
        System.out.println("response recive req"+line);

        if (line == null) {
            System.err.println("Error: No data received. The server may have closed the connection.");
            return null; // Return null if no data
        }

        if (line.isEmpty()) {
            System.err.println("Error: Received empty response.");
            return null;
        }

        System.out.println("Line read: " + line);
        return line; // Return the valid line of data received
    } catch (SocketTimeoutException e) {
        System.err.println("Error: Socket timed out while waiting for input.");
        return null; // Return null on timeout
    } catch (IOException e) {
        System.err.println("Error: An I/O exception occurred while reading input.");
        e.printStackTrace();
        throw e; // Re-throw the exception if necessary
    }
}
    
   public void closeConnection() {
    try {
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null && !socket.isClosed()) socket.close();
    } catch (IOException e) {
        System.err.println("Error closing client resources: " + e.getMessage());
    }
}
   
   
    
}
