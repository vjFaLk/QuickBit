package MainPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller
        implements Initializable {

    @FXML
    private Button searchButton, downloadButton;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox torrentComboBox;
    @FXML
    private StackPane stackPane;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final String torrentName = nameText.getText();
                readFeed(torrentName);

            }
        });


        downloadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        String torrentName = tempList.get(selectedIndex);
        LinkHandler linkHandler = new LinkHandler();
        if (torrentData.isUsingFallbackRSSFeed())
            linkHandler.openWebLink(torrentName);
        else
            linkHandler.openMagnetLink(torrentName);
    }


    private void readFeed(String torrentName) {
        FeedReader feedReader = new FeedReader(torrentName);
        feedReader.Initialize();
        TorrentData torrentData = TorrentData.getInstance();
        ObservableList<String> torrentList = FXCollections.observableArrayList(torrentData.getTorrentLinkList());
        torrentComboBox.setItems(torrentList);
        torrentComboBox.getSelectionModel().select(0);
        if (torrentList.size() > 0)
            downloadButton.setDisable(false);
        else
            downloadButton.setDisable(true);

    }

    private void showWaitingCursor() {
        stackPane.setCursor(Cursor.WAIT);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stackPane.setCursor(Cursor.DEFAULT);
    }

}