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
    
    public void sendMove(String btnId) {
    String message = Encapsulator.encapsulateGameMove(player1Id, player2Id, btnId);
    boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());

    if (!result) {
        System.out.println("Server is not available!");
    }

    try {
        System.out.println("Connection to server is OK!!");
        connection.sendRequest(message);
    } catch (IOException ex) {
        Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, "Error communicating with server", ex);
        }
    }
    
    public boolean updateScore(int score){
        String message = Encapsulator.encapsulateUpdateScore(player1Id, score);
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        try {
            if (!result) {
                System.out.println("Server is not available!");
                return false;
            }
            System.out.println("Connection to server is OK!!");
            connection.sendRequest(message);
    
        } catch (Exception e) {
        }
        return false;
    }
    

}
