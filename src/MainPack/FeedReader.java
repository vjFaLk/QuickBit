package MainPack;

import RSSParser.Feed;
import RSSParser.FeedMessage;
import RSSParser.RSSFeedParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FeedReader {

    private String torrentName;
    private Feed feed;
    private ArrayList<String> magnetLinkList, torrentNameList, torrentSeedList, torrentLeechList, torrentSizeList;


    public FeedReader(String torrentName) {
        this.torrentName = torrentName;
    }

    public void Initialize() {
        readPrint();
        createTorrentList();
    }

    public Feed readPrint() {
        RSSFeedParser parser = null;
        TorrentData torrentData = TorrentData.getInstance();

        try {
            if (checkAvailability()) {
                parser = new RSSFeedParser("http://tf.maxters.net/pbay/search/" + torrentName + "/0/7/0");
                feed = parser.readFeed();
            } else {
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


        TorrentData torrentData = TorrentData.getInstance();

        for (FeedMessage message : feed.getMessages()) {

            torrentNameList.add(message.getTitle());
            magnetLinkList.add(message.getLink());
            torrentSeedList.add(message.getSeeds());
            torrentLeechList.add(message.getLeeches());
            torrentSizeList.add(message.getSize());

        }

        torrentData.setTorrentLinkList(torrentNameList);
        torrentData.setMagnetLinkList(magnetLinkList);
        torrentData.setTorrentSizeList(torrentSizeList);
        torrentData.setTorrentSeedList(torrentSeedList);
        torrentData.setTorrentLeechList(torrentLeechList);


    }

    private boolean checkAvailability() throws IOException {

        final URL url = new URL("http://tf.maxters.net/pbay/search/" + torrentName + "/0/7/0");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();


        if (responseCode == 200) {
            return true;
        } else {
            return false;
        }
    }

    }

