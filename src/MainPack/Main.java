package MainPack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainLayout.fxml"));
        primaryStage.setTitle("QuickBit");
        primaryStage.setScene(new Scene(root, 300, 160));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("download.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
