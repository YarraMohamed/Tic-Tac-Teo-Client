package Controllers;

import Modes.Easy;
import Modes.Hard;
import Modes.Medium;
import Modes.Mode;
import Modes.OnlineGame;
import Utils.Navigation;
import Utils.SharedData;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration; 
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameBoardController implements Initializable {
    
    private Mode pc;
    private String card;
    private Button buttonPressed;
    private int p1Score;
    private int p2Score;
    private int tieScore;
    private boolean isP1Win;
    private boolean isP2Win;
    private boolean winner;
    private Line line;
    private Stage playAgainStage;
    private Stage recordAgainStage;
    private Stage winStage; 

    private static Stage stage;
    private Scene scene;
    private Parent root;

    
   
    
    private int movesMade;
    private GameRecorder gameRecorder;
  
    public  void load( int turn, int p2Id) throws IOException {
        FXMLLoader loader = new FXMLLoader(GameBoardController.class.getResource("/FXML/GameBoard.fxml"));
        Parent root = loader.load();

        // Get the controller and pass necessary data
        GameBoardController controller = loader.getController();
        controller.setTurn(turn);
        controller.setp2ID(p2Id);
        controller.setMode("pvp_online");

        // Set the scene
        Scene scene = new Scene(root);
        stage.setTitle("Tic Tac Toe game");
        stage.setScene(scene);
        stage.show();
    }
       
    @FXML
    private AnchorPane anchorPane;
    
    ///////////////buttons
    
    @FXML
    private Button recordButton;
    @FXML
    private Button leaveButton;
    @FXML
    private Button resetButton;
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
    
    private Navigation nav ;
    
     private Button[][] board;
    
    ///////////////text
    @FXML
    private Text p1Text;
    @FXML
    private Text tieText;
    @FXML
    private Text p2Text;
    
    private String mode ="";
    

    
    
    private int turn;

    private OnlineGame onlineGame;
    private int p2ID;
    private Thread thread;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        turn=1;
        resetGame();
        
        board = new Button[3][3];
        board[0][0] = sqOneXo;
        board[0][1] = sqTwoXo;
        board[0][2] = sqThreeXo;
        board[1][0] = sqFourXo;
        board[1][1] = sqFiveXo;
        board[1][2] = sqSixXo;
        board[2][0] = sqSevenXo;
        board[2][1] = sqEightXo;
        board[2][2] = sqNineXo;
        card="X";
        p1Score = 0;
        p2Score = 0;
        tieScore = 0;
        isP1Win = false;
        isP2Win = false;
        winner = false;
        line = null;
        movesMade = 0;
        gameRecorder = null;
        nav = new Navigation();
    } 
    
    /*
    public void leaveButtonAction(ActionEvent e){
    
    
    }*/ 
    public void setTurn(int n){
        turn=n;
    }
    
    
    public void setp2ID(int id){
        p2ID=id;
    }
    public void setMode(String m){
        this.mode = m;
        initializeMode();
    }
    public void initializeMode(){
//        resetButton.setDisable(false);
        switch (mode) {
            case "pc_Easy":
                pc = new Easy(board, 'O', 'X');
//                handlePcMove(choosenBtn);
                break;
            case "pc_Medium":
                pc = new Medium(board, 'O', 'X');
//                handlePcMove(choosenBtn);
                break;
            case "pc_Hard":
                pc = new Hard(board, 'O', 'X');
//                handlePcMove(choosenBtn);
                break;
            case "pvp_online":
                onlineGame=new OnlineGame(p2ID);
                resetButton.setDisable(true);
                waitP2Move();
                break;
        }
    }

    public void resetButtonAction(ActionEvent e){    
        resetGame();
        
    } 
    
    public void resetGame(){
        turn=1;
        sqOneXo.setText("");
        sqTwoXo.setText("");
        sqThreeXo.setText("");
        sqFourXo.setText("");
        
        sqFiveXo.setText("");
        sqSixXo.setText("");
        sqSevenXo.setText("");
        sqEightXo.setText("");
        sqNineXo.setText("");

        if(line != null){
            anchorPane.getChildren().remove(line);
            line = null;
        }
        card = "X";
    }
    
    public void recordButtonAction(ActionEvent recordGameAction){
                
        if (gameRecorder == null) {
            gameRecorder = new GameRecorder();    
            gameRecorder.prepareRecordingFile();
        }
        recordButton.setDisable(true);
    }
       
   public void gamePlayAction(ActionEvent e) {
    if (!winner && turn ==1) {
        buttonPressed = (Button) e.getSource();

        if (buttonPressed != null && buttonPressed.getText().equals("")) {
            // Save movement if game recorder is initialized
            if (gameRecorder != null) {
                gameRecorder.saveMovement(buttonPressed.getId(), card);
            }

            // Mark the button with the current player's card
            buttonPressed.setText(card);
             movesMade++;
             if(movesMade > 0 && gameRecorder == null){
                recordButton.setDisable(true);
             }
            
            updateButtonStyle(buttonPressed);
             if ("pvp_online".equals(mode)) {
                onlineGame.sendMove(buttonPressed.getId());
            }
             
            checkState();
            
            card = (card.equals("X")) ? "O" : "X";
            turn=2;
            System.out.println("turn is : "+turn);
            
        } else {
            System.out.println("Error: Button is either null or already marked.");
        }
    }

    // If no winner yet, process AI's move
    if (!winner && turn==2) {
        Button choosenBtn = null;
        switch (mode) {
            case "pvp_local":
                turn=1;
                break;
            case "pc_Easy":
//                pc = new Easy(board, 'O', 'X');
                handlePcMove(choosenBtn);
                break;
            case "pc_Medium":
//                pc = new Medium(board, 'O', 'X');
                handlePcMove(choosenBtn);
                break;
            case "pc_Hard":
//                pc = new Hard(board, 'O', 'X');
                handlePcMove(choosenBtn);
                break;
            case "pvp_online":
                p2Text.setText("Waitting...");
                p1Text.setText("palyer 1");

                break;
            }
        }
    }

    private static boolean hasNewMove;
    private static String newMove;
    public static void updateBoard(String btn){
        newMove=btn;
        hasNewMove=true;
        System.out.println("Game Board recived :  "+newMove+" "+hasNewMove);
    }

    private void waitP2Move() {
        thread = new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("thread running......");
                    if (hasNewMove) {
                        System.out.println("has new move>>>>>"+hasNewMove);
                        Platform.runLater(()->{
                            hasNewMove=false;
                            processOpponentMove(newMove);
                        });
                    }
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        });

        thread.setDaemon(true); 
        thread.start();
    }


    public void processOpponentMove(String response) {
        Button receivedBtn = findButtonById(response);
        if (receivedBtn != null && !winner) {
            if (gameRecorder != null) {
                gameRecorder.saveMovement(buttonPressed.getId(), card);
            }
            receivedBtn.setText(card);
            updateButtonStyle(receivedBtn);
            p2Text.setText("Player 2");
            p1Text.setText("Waitting....");
            checkState();
            card = card.equals("X") ? "O" : "X";
            turn = 1;
        }
    }

    public Button findButtonById(String Id){
        switch(Id){
            case "sqOneXo":
                return sqOneXo;
            case "sqTwoXo":
                return sqTwoXo;
            case "sqThreeXo":
                return sqThreeXo;
            case "sqFourXo":
                return sqFourXo;
            case "sqFiveXo":
                return sqFiveXo;
            case "sqSixXo":
                return sqSixXo;
            case "sqSevenXo":
                return sqSevenXo;
            case "sqEightXo":
                return sqEightXo;
            case "sqNineXo":
                return sqNineXo;
            default:
                return null;
        }
    }
    private void handlePcMove(Button choosenBtn){
           // Get AI move and update the board
            if (pc != null) {
                int[] move = pc.getMove();
                int row = move[0];
                int col = move[1];
                choosenBtn = clikedButton(row, col);

                if (choosenBtn != null) {
                    if (gameRecorder != null) {
                        gameRecorder.saveMovement(buttonPressed.getId(), card);
                    }
                    choosenBtn.setText(card);
                    updateButtonStyle(choosenBtn);

                    // Switch turns between players
                    card = (card.equals("X")) ? "O" : "X";
                    turn=1;
                    checkState();
                } else {
                    System.out.println("Error: Button at position (" + row + "," + col + ") is not valid.");
                }
            } else {
                System.out.println("Error: PC mode is not set correctly.");
            }

        if(winner){
            movesMade = 0;
            resetButton.setDisable(true);
            leaveButton.setDisable(true);
        }

    }
    // Method to update button style based on card
    private void updateButtonStyle(Button button) {
        button.setStyle((card.equals("X")) ? "-fx-text-fill: Black;" : "-fx-text-fill: #FFA500;");
    }

    private Button clikedButton(int row,int col){
        if(row == 0){
            switch(col){
                case 0:{
                    return sqOneXo;
                }
                case 1:{
                    return sqTwoXo;
                }
                case 2:{
                    return sqThreeXo;
                }
            }
        }else if(row==1){
            switch(col){
                case 0:{
                    return sqFourXo;
                }
                case 1:{
                    return sqFiveXo;
                }
                case 2:{
                    return sqSixXo;
                }
            }
        }else if(row==2){
            switch(col){
                case 0:{
                    return sqSevenXo;
                }
                case 1:{
                    return sqEightXo;
                }
                case 2:{
                    return sqNineXo;
                }
            }
        }
        return null;
    }
    public void drawLine(Button startButton, Button endButton){
        Bounds bound1 = startButton.localToScene(startButton.getBoundsInLocal());
        Bounds bound2 = endButton.localToScene(endButton.getBoundsInLocal());
        double x1 = (bound1.getMaxX() + bound1.getMinX())/2;
        double y1 = (bound1.getMaxY() + bound1.getMinY())/2;
        double x2 = (bound2.getMaxX() + bound2.getMinX())/2;
        double y2 = (bound2.getMaxY() + bound2.getMinY())/2;
        line = new Line (x1,y1,x2,y2);
        line.setStroke(Color.RED);
        line.setStrokeWidth(10);
        anchorPane.getChildren().add(line);
    }
    
    private void checkRows(){
    
        if(sqOneXo.getText().equals(sqTwoXo.getText()) && sqTwoXo.getText().equals(sqThreeXo.getText()) && !sqOneXo.getText().equals("")){
           if(line == null){
                drawLine(sqOneXo,sqThreeXo);
                
                if(sqOneXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
            }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
            }
           }
            
            winner = true;
        
        }else if(sqFourXo.getText().equals(sqFiveXo.getText()) && sqFiveXo.getText().equals(sqSixXo.getText()) && !sqFourXo.getText().equals("")){
          
            if(line == null){
                drawLine(sqFourXo,sqSixXo);
                
                if(sqFourXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
             }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
             } 
           }
            
            winner = true;
        }else if(sqSevenXo.getText().equals(sqEightXo.getText()) && sqEightXo.getText().equals(sqNineXo.getText())&& !sqSevenXo.getText().equals("")){
            if(line == null){
                drawLine(sqSevenXo,sqNineXo);
                
                if(sqSevenXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
            }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
            }
           }
            
            winner = true;
        
        }
    
    }
    
    private void checkColumns(){
    
        if(sqOneXo.getText().equals(sqFourXo.getText()) && sqFourXo.getText().equals(sqSevenXo.getText()) && !sqOneXo.getText().equals("")){
           if(line == null){
                drawLine(sqOneXo,sqSevenXo);
            
                if(sqOneXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
            }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
            }
           }
           
            winner = true;
 
        }else if(sqTwoXo.getText().equals(sqFiveXo.getText()) && sqFiveXo.getText().equals(sqEightXo.getText()) && !sqTwoXo.getText().equals("")){
            if(line == null){
            drawLine(sqTwoXo,sqEightXo);
            }
            if(sqTwoXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
            }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
            }
            winner = true;
        
        }else if(sqThreeXo.getText().equals(sqSixXo.getText()) && sqSixXo.getText().equals(sqNineXo.getText())&& !sqThreeXo.getText().equals("")){
            if(line == null){
            drawLine(sqThreeXo,sqNineXo);
            }
            if(sqThreeXo.getText().equals("X")){
            
                isP1Win = true;
                p1Score += 1;
                p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
            }else{
            
                isP2Win = true;
                p2Score += 1;
                p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
            }
            winner = true;
        }
    
    }
    
    private void checkDiagonals(){
    
        if(sqOneXo.getText().equals(sqFiveXo.getText()) && sqFiveXo.getText().equals(sqNineXo.getText()) && !sqOneXo.getText().equals("")){
            if(line == null){
                    drawLine(sqOneXo,sqNineXo);
                    if(sqOneXo.getText().equals("X")){
            
                        isP1Win = true;
                         p1Score += 1;
                        p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
                }else{
            
                        isP2Win = true;
                        p2Score += 1;
                        p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
                }
            
            }
                        
            winner = true;
        }else if(sqThreeXo.getText().equals(sqFiveXo.getText()) && sqFiveXo.getText().equals(sqSevenXo.getText()) && !sqThreeXo.getText().equals("")){
            if(line == null){
                drawLine(sqThreeXo,sqSevenXo);
                
                if(sqThreeXo.getText().equals("X")){
            
                  isP1Win = true;
                    p1Score += 1;
                    p1Text.setText("Player 1 : " + String.valueOf(p1Score));
            
             }else{
            
                  isP2Win = true;
                    p2Score += 1;
                    p2Text.setText("Player 2 : " + String.valueOf(p2Score));
                
                }
            }
            
            winner = true;
        }
    
    }
    
    public boolean isFull(){
        
        if(!sqOneXo.getText().equals("")&&!sqTwoXo.getText().equals("")&&!sqThreeXo.getText().equals("")&&!sqFourXo.getText().equals("")
                &&!sqFiveXo.getText().equals("")&&!sqSixXo.getText().equals("")&&!sqSevenXo.getText().equals("")&&!sqEightXo.getText().equals("")
                &&!sqNineXo.getText().equals("")){
        
            return true;
            
        }else{
        
            return false;
        
        }
    
    }
    
    public void winAnimation() {
    String winMessage = null;
    String winVideo = null;

    winStage = new Stage();
    winStage.setTitle("Game Over");
    
    if (mode.equals("pvp_online")) {
            if (isP1Win) {
                winMessage = "Player One Wins!!";
                winVideo = "/Resources/player1Wins.mp4";
            }else{
                winMessage = "Player Two Wins!!";  
                winVideo = "/Resources/loser.mp4"; 
            }
    }else if (isP1Win) {
        winMessage = "Player One Wins!!";
        winVideo = "/Resources/player1Wins.mp4";
    } else if (pc != null && pc.isComputerPlayerWon()) {
        winMessage = "Computer Wins!!";  // Adjusted message
        winVideo = "/Resources/loser.mp4";  // No video for computer win
        playAgainWindow();
        playAgainStage.close();

    } else {
        winMessage = "Player Two Wins!!";
        winVideo = "/Resources/player2Wins.mp4";
    }

    // Make sure winVideo is not null before attempting to play the media
    if (winVideo != null) {
        try {
            Media media = new Media(getClass().getResource(winVideo).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaPlayer.setAutoPlay(true);

            Text winText = new Text(winMessage);
            winText.setFont(Font.font("Chewy", FontWeight.BOLD, 35));
            winText.setFill(Color.RED);

            StackPane root = new StackPane(mediaView, winText);
            StackPane.setAlignment(winText, Pos.TOP_CENTER);
            Scene scene = new Scene(root, 800, 450);
            winStage.setScene(scene);
            winStage.show();

            winStage.setOnCloseRequest(event -> {
                mediaPlayer.stop();
                if (mode.equals("pvp_online")) {
                    try {
                        nav.goToPage("ModePage", p1Text);
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    playAgainWindow();
                };
            });
        } catch (Exception e) {
            // Handle potential errors in loading the video or media file
            System.err.println("Error loading media: " + e.getMessage());
            // You could also show a default message or video in case of an error
        }
    } else {
        // If no win video is available, just show the message
        Text winText = new Text(winMessage);
        winText.setFont(Font.font("Chewy", FontWeight.BOLD, 50));
        winText.setFill(Color.RED);

        StackPane root = new StackPane(winText);
        StackPane.setAlignment(winText, Pos.TOP_CENTER);
        Scene scene = new Scene(root, 800, 450);
        winStage.setScene(scene);
        winStage.show();

        winStage.setOnCloseRequest(event -> {
            playAgainWindow();
        });
    }
}
    
    public void recordAgainWindow() {
        
        recordAgainStage = new Stage();
        recordAgainStage.setTitle("Message From Recorder");
        
        recordAgainStage.initStyle(StageStyle.UNDECORATED);

        Text recordAgainText = new Text("Continue Recording?");
        recordAgainText.setFont(Font.font("Chewy", FontWeight.BOLD, 50));
        recordAgainText.setFill(Color.WHITE);

        Button sureButton = new Button("Sure!");
        Button nopeButton = new Button("Nope");
        
        sureButton.setOnAction(e->recordAgainAction(e));
        nopeButton.setOnAction(e->recordAgainAction(e));
        
        sureButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
        sureButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");

        nopeButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
        nopeButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");
 
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(recordAgainText);

        HBox buttonBox = new HBox(30);
        buttonBox.getChildren().addAll(sureButton, nopeButton);
        buttonBox.setAlignment(Pos.CENTER); 

        borderPane.setBottom(buttonBox);
        
        borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #86AEE9, #09316D);");

        Scene scene = new Scene(borderPane, 450, 250);

        recordAgainStage.setScene(scene);
        recordAgainStage.show();
    }
    
    
    public void recordAgainAction(ActionEvent e) {
    
        buttonPressed = (Button) e.getSource();
    
            if (buttonPressed.getText().equals("Sure!")) {
            
                gameRecorder.prepareRecordingFile();
                recordAgainStage.close();
            
            } else if (buttonPressed.getText().equals("Nope")) {
            
                gameRecorder = null;
                recordButton.setDisable(true);
                recordAgainStage.close();
            
            }
    }
    
    
    public void playAgainWindow() {
        
        playAgainStage = new Stage();
        playAgainStage.setTitle("Run it Back");
        playAgainStage.initStyle(StageStyle.UNDECORATED);


        Text againText = new Text("Another Game?");
        againText.setFont(Font.font("Chewy", FontWeight.BOLD, 50));
        againText.setFill(Color.WHITE);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        
        yesButton.setOnAction(e -> {
                againAction(e);

                if (gameRecorder != null) {
                    recordAgainWindow();
                }
                            
        });
        
        noButton.setOnAction(e->againAction(e));
        
        yesButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
        yesButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");

        noButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
        noButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");
 
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(againText);

        HBox buttonBox = new HBox(30);
        buttonBox.getChildren().addAll(yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER); 

        borderPane.setBottom(buttonBox);
        
        borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #86AEE9, #09316D);");

        Scene scene = new Scene(borderPane, 400, 225);
        
        playAgainStage.setScene(scene);
        playAgainStage.show();
    }
    
    
    public void againAction(ActionEvent e){
    
        buttonPressed = (Button) e.getSource();
        
        if(buttonPressed.getText().equals("Yes")){
                
            resetGame();
            winner = false;
            isP1Win = false;
            isP2Win = false;
            playAgainStage.close();
            if(gameRecorder == null){recordButton.setDisable(false);}   
            resetButton.setDisable(false);
            leaveButton.setDisable(false);
                    
        }else if(buttonPressed.getText().equals("No")){
            leaveButton.setDisable(false);
            playAgainStage.close();

           playAgainStage.close();  
        }
    }
    
    
    public void checkState(){
            
        checkRows();
        checkColumns();
        checkDiagonals();
        if (mode.equals("pvp_online")) {
            if (isP1Win||isP2Win) {
                if (turn==1) {
                    isP1Win=true;
                    isP2Win=false;
                    onlineGame.updateScore(100);
                }else{
                    isP1Win=false;
                    isP2Win=true;
                    onlineGame.updateScore(-100);
                }
                thread.interrupt();
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(e->winAnimation());           
                pause.play();
            }
            
        }else if(isP1Win || isP2Win){
            System.out.println("player1 => "+isP1Win+"  palyer2 => "+isP2Win);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e->winAnimation());           
            pause.play();
        }else if(isFull()){
            tieScore += 1;
            tieText.setText("Tie : " + String.valueOf(tieScore));
            resetButton.setDisable(true);
            leaveButton.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e->winAnimation());           
            pause.play();
        }
        
    }

    
    public void exitGame(ActionEvent event) {
    Stage confirmExitStage = new Stage();
    confirmExitStage.initStyle(StageStyle.UNDECORATED);
    confirmExitStage.setTitle("Confirm Exit");

    Text confirmText = new Text("Are you sure you want to exit?");
    confirmText.setFont(Font.font("Chewy", FontWeight.BOLD, 40));
    confirmText.setFill(Color.WHITE);

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    yesButton.setOnAction(e -> {
        confirmExitStage.close();
        try {
            if(SharedData.getInstance().getPlayerID()!=0){
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModePage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }else{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/DifficultyPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    noButton.setOnAction(e -> confirmExitStage.close());

    yesButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
    yesButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");
    noButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
    noButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");

    HBox buttonBox = new HBox(30, yesButton, noButton);
    buttonBox.setAlignment(Pos.CENTER);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(confirmText);
    borderPane.setBottom(buttonBox);
    borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #86AEE9, #09316D);");

    Scene scene = new Scene(borderPane, 500, 250);
    confirmExitStage.setScene(scene);
    confirmExitStage.showAndWait(); 
}


}
        

