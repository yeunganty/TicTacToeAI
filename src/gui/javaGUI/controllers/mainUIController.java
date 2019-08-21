package gui.javaGUI.controllers;

import ai.BotMiniMax;
import gui.javaGUI.AlertBox;
import gui.javaGUI.TicTacToeJavaGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import model.TicTacToe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class mainUIController implements   Initializable {


    @FXML
    GridPane grid;

    @FXML
    Label P1;
    @FXML
    Label P2;
    @FXML
    Label notification;

    private TicTacToe game;

    private Background imgX;
    private Background imgO;

    private boolean botGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new TicTacToe();
        botGame = TicTacToe.isBotGame();
        initializeSymbols();
        setupGridPane();
        setupLabels();
    }

    private void initializeSymbols() {
        Image ImageO = new Image(getClass().getResourceAsStream("/gui/javaGUI/resources/Images/O-red.png"));
        Image ImageX = new Image(getClass().getResourceAsStream("/gui/javaGUI/resources/Images/X-red.png"));
        imgX = new Background(new BackgroundImage(ImageX,null, null, null, null));
        imgO = new Background(new BackgroundImage(ImageO,null, null, null, null));
    }

    private void setupGridPane() {
        for (Node label : grid.getChildren()) {
            label.setOnMouseClicked(e ->
            { System.out.println("You chose " + label.getId());
                updateGame((Label) label);
                highlightCurrentTurn();
                checkGameState();
            });
        }
    }

    private void botMove() {
        if (botGame && game.validGameState()) {
            BotMiniMax ai = new BotMiniMax(game.getCurrentTurn());
            ai.miniMax(game,0);
            updateUI();
            highlightCurrentTurn();
        }
    }

    private void updateUI() {

        List<String> spots = game.getGame();
        for (int i=0;i<9;i++) {
            if (!spots.get(i).equals("_")) {
                Label label = (Label) grid.getChildren().get(i);
                if (spots.get(i).equals("X"))
                    label.setBackground(imgX);
                else
                    label.setBackground(imgO);
            }
        }
    }

    private void checkGameState() {
        if (!game.validGameState()) {
            if (game.getWinner().equals("X")) {
                System.out.println("X won");
                TicTacToe.setP1Score(TicTacToe.getP1Score()+1);
                AlertBox.display("X won the game. \n Play again?", true);
            } else if (game.getWinner().equals("O")) {
                System.out.println("O won");
                TicTacToe.setP2Score(TicTacToe.getP2Score()+1);
                AlertBox.display("O won the game. \n Play again?", true);
            } else {
                System.out.println("Tie Game");
                AlertBox.display("Tie game. \n Play again?", true);
            }
            P1.setText("("+TicTacToe.p1+")"+ " P1: " + TicTacToe.getP1Score());
            P2.setText("("+TicTacToe.p2+")" + (botGame? " Bot: ": " P2: ") + TicTacToe.getP2Score());
        }

    }

    private void updateGame(Label label) {
        String position = label.getId();
        int pos = Integer.parseInt(position);
        String currentTurn = game.getCurrentTurn();
        if (game.validGameState()) {
            if (game.move(pos)) {
                notification.setText("");
                if (currentTurn.equals("X"))
                    label.setBackground(imgX);
                else
                    label.setBackground(imgO);
                botMove();
            } else
                notification.setText("Invalid Move");
        }
    }

    private void setupLabels() {
        notification.setText("");
        P1.setText("("+TicTacToe.p1+")"+ " P1: " + TicTacToe.getP1Score());
        P2.setText("("+TicTacToe.p2+")" + (botGame? " Bot: ": " P2: ") + TicTacToe.getP2Score());
        highlight(P1);
        unhighlight(P2);
    }

    private void highlightCurrentTurn() {
        if (game.getCurrentTurn().equals(TicTacToe.p1)) {
            highlight(P1);
            unhighlight(P2);
        } else {
            highlight(P2);
            unhighlight(P1);
        }
    }

    private void highlight(Label label) {
        label.setStyle("-fx-background-color:rgba(85, 255, 68,0.7);");
    }

    private void unhighlight(Label label) {
        label.setStyle("-fx-background-color:rgba(0,0,255,0);");
    }

    @FXML
    private void setNewGame() {
        AlertBox.display("Start a new game?", false);
    }


    public void setPlayFriend(ActionEvent actionEvent) {
        TicTacToe.setBotGame(false);
        resetStage();
    }

    public void setPlayBot(ActionEvent actionEvent) {
        TicTacToe.setBotGame(true);
        resetStage();
    }

    private void resetStage() {
        TicTacToeJavaGUI ttt = new TicTacToeJavaGUI();
        TicTacToe.setBotScore(0);
        TicTacToe.setP1Score(0);
        TicTacToe.setP2Score(0);
        ttt.setScene();
    }

    public void quitGame(ActionEvent actionEvent) {
        TicTacToeJavaGUI.closeStage();
        TicTacToe.setBotScore(0);
        TicTacToe.setP1Score(0);
        TicTacToe.setP2Score(0);
    }
}
