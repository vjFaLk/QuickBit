package MainPack;

import RSSParser.Feed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller
        implements Initializable {

    @FXML
    private Button searchButton, downloadButton, openButton;
    @FXML
    private ComboBox nameText;
    @FXML
    private ComboBox torrentComboBox;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ToggleButton autoToggle;

    private boolean isFeedRead = false;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {


        nameText.getStyleClass().add("combo-box1");

        setTorrentNameComboBox();

        autoToggle.setSelected(true);


        nameText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if (key.getCode().equals(KeyCode.ENTER)) {
                    searchTorrent();
                }
            }
        });

        new AutoCompleteComboBoxListener<>(nameText);

        searchButton.setOnAction(event -> searchTorrent());


        downloadButton.setOnAction(event -> openMagnetLink(torrentComboBox.getSelectionModel().getSelectedIndex()));

        torrentComboBox.setOnAction(event -> {
            if (isFeedRead) {
                showDescription();
            }
        });

        openButton.setOnAction(event -> openPageLink(torrentComboBox.getSelectionModel().getSelectedIndex()));

        setToolTips();


    }

    private void searchTorrent() {
        final String torrentName = nameText.getSelectionModel().getSelectedItem().toString();
        isFeedRead = false;
        FileHandler file = new FileHandler();
        file.addToAutoCompleteList(torrentName);
        readFeed(torrentName);
        setTorrentNameComboBox();
        isFeedRead = true;
    }

    private void setToolTips() {

        openButton.setTooltip(new Tooltip("Open the webpage for the torrent"));
        autoToggle.setTooltip(new Tooltip("Instantly add the best torrent to your BitTorrent client"));
        downloadButton.setTooltip(new Tooltip("Add selected torrent to your BitTorrent client"));
    }

    private void setTorrentNameComboBox() {
        FileHandler file = new FileHandler();
        ObservableList<String> torrentList = FXCollections.observableArrayList(file.getTorrentList());
        nameText.setItems(torrentList);
    }

    private void openMagnetLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        tempList = torrentData.getMagnetLinkList();
        String torrentName = tempList.get(selectedIndex);
        LinkHandler linkHandler = new LinkHandler();
        if (torrentData.isUsingFallbackRSSFeed()) {
            HTMLParser parse = new HTMLParser();
            String magnetLink = parse.parseLink(torrentName);
            parse.parseForMagnetLink(magnetLink);
        } else
            linkHandler.openMagnetLink(torrentName);
    }


    private void openPageLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        LinkHandler linkHandler = new LinkHandler();
        HTMLParser parse = new HTMLParser();

        if (torrentData.isUsingFallbackRSSFeed()) {
            tempList = torrentData.getMagnetLinkList();
            String torrentName = tempList.get(selectedIndex);
            String pageLink = parse.parseLink(torrentName);
            linkHandler.openWebLink(pageLink);
        } else {
            tempList = torrentData.getTorrentPageLinksList();
            String torrentName = tempList.get(selectedIndex);
            torrentName = linkHandler.addSuffix(torrentName);
            linkHandler.openWebLink(torrentName);
        }

    }


    private void readFeed(String torrentName) {
        FeedReader feedReader = new FeedReader();
        Feed feed = feedReader.getFeed(torrentName);
        feedReader.createTorrentDataLists(feed);
        // feedReader.removeDeadLinks();
        TorrentData torrentData = TorrentData.getInstance();
        ObservableList<String> torrentList = FXCollections.observableArrayList(torrentData.getTorrentNameList());
        setTorrentComboBox(torrentList);

        if (autoToggle.isSelected()) {
            openMagnetLink(0);
        }
    }


    private void setTorrentComboBox(ObservableList torrentList) {
        torrentComboBox.setItems(torrentList);
        torrentComboBox.getSelectionModel().select(0);
        if (torrentList.size() > 0) {
            downloadButton.setDisable(false);
            openButton.setDisable(false);
            showDescription();
        } else {
            downloadButton.setDisable(true);
            openButton.setDisable(true);
        }
    }

    private void showDescription() {
        TorrentData torrentData = TorrentData.getInstance();
        int index = torrentComboBox.getSelectionModel().getSelectedIndex();
        descriptionLabel.setAlignment(Pos.CENTER);
        if (torrentData.getTorrentNameList().size() > 0) {
            if (torrentData.isUsingFallbackRSSFeed()) {
                descriptionLabel.setText(torrentData.getTorrentDescriptionList().get(index));
            } else {
                descriptionLabel.setText("Size: " + torrentData.getTorrentSizeList().get(index) +
                        " Seeds: " + torrentData.getTorrentSeedList().get(index) +
                        " Peers: " + torrentData.getTorrentLeechList().get(index));
            }
        }

    }

}