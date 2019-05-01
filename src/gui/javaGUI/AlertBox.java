package gui.javaGUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.TicTacToe;

public class AlertBox {
    private static final String yes = "Yes";
    private static final String no = "No";
    private static final int windowWidth = 250;
    private static final int windowHeight = 150;

    public static void display(String title, boolean closeWindow) {
        defaultDisplay(title, yes, no, closeWindow);
    }

    public static void defaultDisplay(String title, String first, String second, boolean closeWindow) {
        Stage window = new Stage();

        //Prevents any other action being done until this is completed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("");
        window.setMinWidth(windowWidth);
        window.setMinHeight(windowHeight);

        Label label = new Label();
        label.setText(title);
        Button YesButton = new Button(first);
        //add more function usingn {}
        YesButton.setOnAction(e -> {
            TicTacToeJavaGUI ttt = new TicTacToeJavaGUI();
            ttt.setScene();
            window.close();
        });

        Button NoButton = new Button(second);
        //add more function using {}
        NoButton.setOnAction(e -> {
            window.close();
            if (closeWindow)
                TicTacToeJavaGUI.closeStage();
                TicTacToe.setBotScore(0);
                TicTacToe.setP1Score(0);
                TicTacToe.setP2Score(0);
        });
        HBox options = new HBox(10);
        options.getChildren().addAll(YesButton, NoButton);
        options.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, options);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        centerStage(window);
        window.setScene(scene);
        window.show();
    }

    public static void customButtonDisplay(String title, Button leftButton, Button rightButton) {
        Stage window = new Stage();

        //Prevents any other action being done until this is completed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("");
        window.setMinWidth(windowWidth);
        window.setMinHeight(windowHeight);

        Label label = new Label();
        label.setText(title);
        HBox options = new HBox(10);
        options.getChildren().addAll(leftButton, rightButton);
        options.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, options);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        centerStage(window);
        window.setScene(scene);
        window.show();
    }

    private static void centerStage(Stage window) {
        Stage primaryStage = TicTacToeJavaGUI.getPrimaryStage();
        double X = primaryStage.getX();
        double Y = primaryStage.getY();
        double Xlength = primaryStage.getWidth();
        double Ylength = primaryStage.getHeight();
        window.setX(X+Xlength/2-windowWidth/2);
        window.setY(Y+Ylength/2-windowHeight/2);
    }
}
