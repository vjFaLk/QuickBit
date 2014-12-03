package MainPack;

import RSSParser.Feed;
import javafx.application.Platform;
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
    private TextField searchText;
    @FXML
    private ComboBox torrentComboBox;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ToggleButton autoToggle;


    private boolean isFeedRead = false;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {


        setToolTips();
        setAutoButton();
        setSearchComboBox();
        setButtons();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                FileHandler file = new FileHandler();
                file.setAutoSettings(autoToggle.isSelected());
            }
        });
    }


    private void setButtons() {
        searchButton.defaultButtonProperty().bind(searchButton.focusedProperty());
        downloadButton.defaultButtonProperty().bind(downloadButton.focusedProperty());
        openButton.defaultButtonProperty().bind(openButton.focusedProperty());

        searchButton.setOnAction(event ->
                searchTorrent());
        downloadButton.setOnAction(event -> openMagnetLink(torrentComboBox.getSelectionModel().getSelectedIndex()));
        torrentComboBox.setOnAction(event -> {
            if (isFeedRead) {
                showDescription();
            }
        });
        openButton.setOnAction(event -> openPageLink(torrentComboBox.getSelectionModel().getSelectedIndex()));
    }


    private void setAutoButton() {
        FileHandler file = new FileHandler();
        autoToggle.setSelected(file.getAutoSetting());
    }


    private void setSearchComboBox() {

        searchText.getStyleClass().add("combo-box1");
        Platform.runLater(() -> searchText.requestFocus());

        searchText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    searchTorrent();
                }
            }
        });
    }

    public void searchTorrent() {

        try {
            String torrentName = searchText.getText();
            System.out.println(torrentName);
            isFeedRead = false;
            torrentName = torrentName.replaceAll(" ", "+");
            readFeed(torrentName);
            Platform.runLater(() -> torrentComboBox.requestFocus());
            isFeedRead = true;
        } catch (NullPointerException e) {
            descriptionLabel.setText("Eh, I got nothing");
        }
    }


    private void setToolTips() {

        openButton.setTooltip(new Tooltip("Open the webpage for the torrent"));
        autoToggle.setTooltip(new Tooltip("Instantly add the best torrent to your BitTorrent client"));
        downloadButton.setTooltip(new Tooltip("Add selected torrent to your BitTorrent client"));
    }


    private void openMagnetLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        tempList = torrentData.getMagnetLinkList();
        String torrentName = tempList.get(selectedIndex);
        LinkHandler linkHandler = new LinkHandler();
        linkHandler.openMagnetLink(torrentName);
    }


    private void openPageLink(int selectedIndex) {
        ArrayList<String> tempList;
        TorrentData torrentData = TorrentData.getInstance();
        LinkHandler linkHandler = new LinkHandler();
        tempList = torrentData.getTorrentPageLinksList();
        String torrentName = tempList.get(selectedIndex);
        torrentName = linkHandler.addSuffix(torrentName);
        linkHandler.openWebLink(torrentName);
        }


    private void readFeed(String torrentName) {
        FeedReader feedReader = new FeedReader();
        Feed feed = feedReader.getFeed(torrentName);
        feedReader.createTorrentDataLists(feed);
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
        descriptionLabel.setText(torrentData.getTorrentDescriptionList().get(index));
    }

}