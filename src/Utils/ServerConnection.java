package Utils;

import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    
    private Socket socket;
    private BufferedReader  in;
    private PrintStream out;
    private static ServerConnection instance;
    
    private ServerConnection(){
    }
    
     public static ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }
    
    public boolean checkServerAvailibily(String serverIP){
        try {
            socket = new Socket(serverIP,5005);
            socket.close();
            return true;
        } catch (IOException ex) {
           return false;
        }
    }
    
    public void openConnection() throws IOException{
        String serverIP = SharedData.getInstance().getServerIp();
        socket = new Socket(serverIP,5005);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }
    
    public String sendRequest(String request) throws IOException {
        String response ="sending data";
        if (socket == null || socket.isClosed()) {
            response = "Connection is not open";
        }
        
        out.println(request);
        out.flush();
        
        response = in.readLine();
        return response;
    }
    
    public void closeConnection() throws IOException{
        if(socket!=null) {
            socket.close();
        }
    }
    
}
