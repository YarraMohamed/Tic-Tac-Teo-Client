package Utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    
    private Socket socket;
    DataInputStream in;
    PrintStream out;
    
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
        in = new DataInputStream(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
    }
    
    public String sendRequest(String request) throws IOException {
        String response;
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
