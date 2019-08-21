package gui.javaGUI.controllers;

import javafx.fxml.Initializable;
import model.TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

public class InitializeGUI implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TicTacToe game = new TicTacToe();
    }
}
