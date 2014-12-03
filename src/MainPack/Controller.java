package MainPack;

import RSSParser.Feed;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller
        implements Initializable {

    @FXML
    private Button searchButton, downloadButton, openButton;
    @FXML
    private ComboBox searchComboBox;
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

        //Adds a shutdown hook that passes the state of the AutoMode Button to be written to a file
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                FileHandler file = new FileHandler();
                file.setAutoSettings(autoToggle.isSelected());
            }
        });
    }


    private void setButtons() {

        // A little hack to make the focused button trigger when the Enter key is pressed
        // Changes the default button to the one that is focused, which causes the button to change color too
        searchButton.defaultButtonProperty().bind(searchButton.focusedProperty());
        downloadButton.defaultButtonProperty().bind(downloadButton.focusedProperty());
        openButton.defaultButtonProperty().bind(openButton.focusedProperty());

        searchButton.setOnAction(event -> searchTorrent());
        downloadButton.setOnAction(event -> openMagnetLink(torrentComboBox.getSelectionModel().getSelectedIndex()));
        torrentComboBox.setOnAction(event -> {
            //Reads the description only when it's confirmed the feed has been read to prevent NullPointerExceptions
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

        searchComboBox.getStyleClass().add("combo-box1");
        Platform.runLater(() -> searchComboBox.requestFocus());

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Movies",
                        "Games",
                        "TV",
                        "Music"
                );

        searchComboBox.setItems(options);

        searchComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Basically if anything from the list is selected this is triggered
                if (searchComboBox.getSelectionModel().getSelectedIndex() != -1) {
                    autoToggle.setSelected(false);
                    searchTorrent();
                }
            }
        });
    }

    public void searchTorrent() {

        try {
            String torrentName = searchComboBox.getSelectionModel().getSelectedItem().toString();
            isFeedRead = false;
            readFeed(torrentName);
            Platform.runLater(() -> torrentComboBox.requestFocus());  //Giving focus to the ComboBox once the searching is completed.
            isFeedRead = true;
        } catch (NullPointerException e) {
            descriptionLabel.setText("Eh, I got nothing");
        }
    }


    private void setToolTips() {
        //There are tooltips in the app?!
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
        // This is the amazing state of the art Independent Intelligent-O-TorrentPicker (IDIOT)
        // It opens the topmost torrent in the list
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