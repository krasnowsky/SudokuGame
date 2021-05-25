package JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;


public class JavaFxMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = StageController.setBundle();
        Pane mainPane = FXMLLoader.load(getClass().getResource("/MainView.fxml"), bundle);
        mainPane.setStyle("-fx-background-image: url('mainViewBG.jpg')");
        Scene newScene = new Scene(mainPane);
        stage.setScene(newScene);
        stage.setTitle("Sudoku");
        stage.getIcons().add(new Image("sudoku_icon.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
