package gui.javaGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeJavaGUI extends Application {
    public static final String TITLE = "TicTacToe";
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setPrimaryStage(primaryStage);
        setScene();
    }

    public void setScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/javaGUI/resources/FXMLFiles/mainUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(TITLE);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Failed to load scene");
            e.printStackTrace();
        }
    }

    // EFFECTS: returns the primary Stage
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // REQUIRES: stage != null
    // MODIFIES: this
    // EFFECTS: sets the primary stage
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void closeStage() {
        primaryStage.close();
    }
}
