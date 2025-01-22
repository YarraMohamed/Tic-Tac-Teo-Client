/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modes;

import Utils.Encapsulator;
import Utils.ServerConnection;
import Utils.SharedData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Ziad
 */
public class OnlineGame {
    int player1Id;
    int player2Id;
    private ServerConnection connection = ServerConnection.getInstance();

    
    
    public OnlineGame(int rivalIp) {
        player1Id= SharedData.getInstance().getPlayerID();
        player2Id= rivalIp;
    }
    /*public boolean sendMove(String btnId){
        String message = Encapsulator.encapsulateGameMove(player1Id,player2Id, btnId);
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        if (result) {
            try {
                System.out.println("connection to server is ok!!");
                connection.openConnection();
                String responseJSON = connection.sendRequest(message);
                JSONObject jsonReceived = new JSONObject(responseJSON);
                String response = jsonReceived.getString("response");
                
                if(response.equals("Success")){
                    System.out.println("Move sent successfully to server");
                } else {
                    System.out.println("something got wrong!!");
                    return false;
                }
            } catch (IOException ex) {
                Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }else{
            return false;
        }

        return true;
    }
    */
    public boolean sendMove(String btnId) {
    String message = Encapsulator.encapsulateGameMove(player1Id, player2Id, btnId);
    boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());

    if (!result) {
        System.out.println("Server is not available!");
        return false;
    }

    try {
        System.out.println("Connection to server is OK!!");
        connection.openConnection();

        // Send request to the server
        String responseJSON = connection.sendRequest(message);

        // Parse and validate JSON response
        JSONObject jsonReceived = new JSONObject(responseJSON);
        if (jsonReceived.has("response")) {
            String response = jsonReceived.getString("response");
            String rmessage = jsonReceived.getString("message");

            if ("Success".equalsIgnoreCase(response)) {
                System.out.println("Move sent successfully to server.");
                return true;
            } else {
                System.out.println("Server returned an error: " + rmessage);
                return false;
            }
        } else {
            System.out.println("Invalid response received from the server: " + responseJSON);
            return false;
        }
    } catch (IOException ex) {
        Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, "Error communicating with server", ex);
        return false;
        }
    }
    public String reciveMove(){
        try {
            String response=connection.reciveRequset();
            JSONObject jsonReceived = new JSONObject(response);
            
            if(jsonReceived.has("requestType")){
                String requestType=jsonReceived.getString("requestType");
                if (requestType.equals("MOVE")) {
                    System.out.println(jsonReceived.getString("btn"));
                    return jsonReceived.getString("btn");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
