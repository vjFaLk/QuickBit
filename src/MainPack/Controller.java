package MainPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private Label descriptionLabel;

    private boolean isFeedRead = false;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {


        searchButton.setOnAction(event -> {
            final String torrentName = nameText.getText();
            isFeedRead = false;
            readFeed(torrentName);
            isFeedRead = true;


        });


        downloadButton.setOnMouseClicked(event -> openMagnetLink(torrentComboBox.getSelectionModel().getSelectedIndex()));

        torrentComboBox.setOnAction(event -> {
            if (isFeedRead) {
                showDescription();
            }
        });


    }

    private void openMagnetLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        tempList = torrentData.getMagnetLinkList();
        String torrentName = tempList.get(selectedIndex);
        LinkHandler linkHandler = new LinkHandler();
        if (torrentData.isUsingFallbackRSSFeed()) {
            HTMLParser parse = new HTMLParser();
            parse.parseLink(torrentName);
        }
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
        if (torrentList.size() > 0) {
            downloadButton.setDisable(false);
            showDescription();
        }
        else
            downloadButton.setDisable(true);
    }

    private void showDescription() {
        TorrentData torrentData = TorrentData.getInstance();
        int index = torrentComboBox.getSelectionModel().getSelectedIndex();
        descriptionLabel.setAlignment(Pos.CENTER);
        if (torrentData.getTorrentLinkList().size() > 0) {
            if (torrentData.isUsingFallbackRSSFeed()) {
                descriptionLabel.setText(torrentData.getTorrentDescriptionList().get(index));
            } else {
                descriptionLabel.setText("Size: " + torrentData.getTorrentSizeList().get(index) +
                        " Seeds: " + torrentData.getTorrentSeedList().get(index) +
                        " Peers: " + torrentData.getTorrentLeechList().get(index));
            }
        }

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