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
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setMaxHeight(200);
        primaryStage.setMaxWidth(300);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(300);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("download.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
