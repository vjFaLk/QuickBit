package MainPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller
        implements Initializable {

    @FXML
    private Button searchButton;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox torrentComboBox;
    @FXML
    private Hyperlink downloadLabel;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {


        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                readFeed(nameText.getText().toString());
            }
        });


        downloadLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                openMagnetLink(torrentComboBox.getSelectionModel().getSelectedIndex());
            }
        });



    }

    private void openMagnetLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        tempList = torrentData.getMagnetLinkList();
        String torrentName = tempList.get(selectedIndex).toString();
        LinkHandler linkHandler = new LinkHandler();
        linkHandler.openPage(torrentName);


    }


    private void readFeed(String torrentName) {
        FeedReader RSS = new FeedReader(torrentName);
        RSS.createMovieList();
        ObservableList<String> movieList = FXCollections.observableArrayList(RSS.createMovieList());
        torrentComboBox.setItems(movieList);
        torrentComboBox.getSelectionModel().select(0);


    }

}