package MainPack;

import RSSParser.Feed;
import RSSParser.FeedMessage;
import RSSParser.RSSFeedParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class FeedReader {

    private String torrentName;
    private Feed feed;
    private ArrayList<String> magnetLinkList, torrentNameList, torrentSeedList, torrentLeechList, torrentSizeList, torrentDescriptionList;
    private boolean UsingBackupRSS;


    public FeedReader(String torrentName) {
        this.torrentName = torrentName;
    }

    public void Initialize() {
        checkAvailability();
        readPrint();
        createTorrentList();
    }

    public Feed readPrint() {
        RSSFeedParser parser = null;
        TorrentData torrentData = TorrentData.getInstance();

        try {
            if (UsingBackupRSS) {
                parser = new RSSFeedParser("http://tf.maxters.net/pbay/search/" + torrentName + "/0/7/0");
                feed = parser.readFeed();
            } else {
                torrentName = torrentName.replace(' ', '+');
                parser = new RSSFeedParser("http://torrentz.eu/feed?q=" + torrentName);
                feed = parser.readFeed();
                torrentData.setUsingFallbackRSSFeed(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return feed;

    }

    public void createTorrentList() {

        torrentNameList = new ArrayList<>();
        magnetLinkList = new ArrayList<>();
        torrentSizeList = new ArrayList<>();
        torrentSeedList = new ArrayList<>();
        torrentLeechList = new ArrayList<>();
        torrentDescriptionList = new ArrayList<>();


        TorrentData torrentData = TorrentData.getInstance();

        for (FeedMessage message : feed.getMessages()) {

            torrentNameList.add(message.getTitle());
            magnetLinkList.add(message.getLink());
            torrentSeedList.add(message.getSeeds());
            torrentLeechList.add(message.getLeeches());
            torrentSizeList.add(message.getSize());
            torrentDescriptionList.add(removeHashText(message.getDescription()));
        }


        torrentData.setTorrentLinkList(torrentNameList);
        torrentData.setMagnetLinkList(magnetLinkList);
        torrentData.setTorrentSizeList(torrentSizeList);
        torrentData.setTorrentSeedList(torrentSeedList);
        torrentData.setTorrentLeechList(torrentLeechList);
        torrentData.setTorrentDescriptionList(torrentDescriptionList);

    }

    private String removeHashText(String Description) {
        if (!UsingBackupRSS) {
            Description = Description.substring(0, Description.length() - 46);
        }
        return Description;
    }

    private void checkAvailability() {

        try {
            final URL url = new URL("http://tf.maxters.net/pbay/search/" + torrentName + "/0/7/0");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode == 200) {
                UsingBackupRSS = true;
            } else {
                UsingBackupRSS = false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

