package gui.javaGUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

public class InitializeGUI implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TicTacToe game = new TicTacToe();
    }

    public void createNewGame(ActionEvent actionEvent) {
    }

    public void closeGame(ActionEvent actionEvent) {
    }

    public void botGame(ActionEvent actionEvent) {
    }

    public void friendGame(ActionEvent actionEvent) {
    }
}
