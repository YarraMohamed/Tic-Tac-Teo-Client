package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;


public class ReplayBoardController {

    @FXML
    private Button sqOneXo;
    @FXML
    private Button sqTwoXo;
    @FXML
    private Button sqThreeXo;
    @FXML
    private Button sqFourXo;
    @FXML
    private Button sqFiveXo;
    @FXML
    private Button sqSixXo;
    @FXML
    private Button sqSevenXo;
    @FXML
    private Button sqEightXo;
    @FXML
    private Button sqNineXo;

    private String fileName;

    @FXML
    public void initialize() {
        sqOneXo.setText("");
        sqOneXo.setDisable(true);
        sqTwoXo.setText("");
        sqTwoXo.setDisable(true);
        sqThreeXo.setText("");
        sqThreeXo.setDisable(true);
        sqFourXo.setText("");
        sqFourXo.setDisable(true);
        sqFiveXo.setText("");
        sqFiveXo.setDisable(true);
        sqSixXo.setText("");
        sqSixXo.setDisable(true);
        sqSevenXo.setText("");
        sqSevenXo.setDisable(true);
        sqEightXo.setText("");
        sqEightXo.setDisable(true);
        sqNineXo.setText("");
        sqNineXo.setDisable(true);
    }

    public void setFileName(String fileName) {
        fileName += ".txt";
        this.fileName = System.getProperty("user.home") + "/TicTacToeRecordings/" + fileName;
        System.out.println(this.fileName);

        replay();
    }

    private boolean checkFile(String path) {
        File temp = new File(this.fileName);
        if(temp.exists()) return true;
        else return false;
    }
    
    public void replay() {
          new Thread(() -> {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String buttonID = parts[0];
                    String buttonChar = parts[1];
                    System.out.println(buttonID + " " + buttonChar);
                    Button button = getButtonById(buttonID);
                    if (button != null) {
                        Platform.runLater(() -> button.setText(buttonChar));
                        Thread.sleep(2000);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ReplayBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }).start();
    }

    private Button getButtonById(String buttonId) {
        switch (buttonId) {
            case "sqOneXo": return sqOneXo;
            case "sqTwoXo": return sqTwoXo;
            case "sqThreeXo": return sqThreeXo;
            case "sqFourXo": return sqFourXo;
            case "sqFiveXo": return sqFiveXo;
            case "sqSixXo": return sqSixXo;
            case "sqSevenXo": return sqSevenXo;
            case "sqEightXo": return sqEightXo;
            case "sqNineXo": return sqNineXo;
            default: return null;
        }
    }
}
