package MainPack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller
        implements Initializable {

    @FXML
    private Button downloadButton;
    @FXML
    private TextField nameText;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert downloadButton != null : "fx:id=\"downloadButton\" was not injected: check your FXML file 'mainLayout.fxml'.";

        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printFeed();
            }
        });

    }

    private void printFeed() {
        FeedReader RSS = new FeedReader("Transformers",1080);
        RSS.printTitle();



    }

}