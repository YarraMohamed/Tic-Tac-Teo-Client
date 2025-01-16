/*
    - I need to have a track of the games that doesn't reset when the game is closed

    - If I make a file to keep track of the games will it be made in this class?
*/
package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameRecorder {
    
    /* Do I need them? */
    // private String buttonId;
    // private String movement;
    private String directoryPath;
    private String fileName;
    
    
    public GameRecorder() {
        this.directoryPath = System.getProperty( "user.home" ) + "/TicTacToeRecordings/";
    }
    
    
    // Create a directory if it does not exist
    private void checkDirectoryExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    
    // Make file for a game recording: makeRecordingFile
    public void prepareRecordingFile() {
        checkDirectoryExists();
        this.fileName = directoryPath + "game_";
    }
    
    
    
    // Save player movements: maybe writeMovementToFile
    public void saveMovement(String buttonId, String movement) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { 
            writer.write(buttonId + "," + movement); 
            writer.newLine();
        } catch (IOException e) { 
            e.printStackTrace(); 
            System.out.println("Error while making the recording file.");
        } 
    } 
    
    
    
    
    
    
}
