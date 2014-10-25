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

    private boolean UsingBackupRSS;


    public FeedReader() {
        checkAvailability();
    }

    public Feed getFeed(String torrentName) {
        RSSFeedParser parser = null;
        Feed feed = null;
        TorrentData torrentData = TorrentData.getInstance();
        try {
            if (!UsingBackupRSS) {
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

    public void createTorrentDataLists(Feed feed) {


        ArrayList<String> NameList = new ArrayList<>();
        ArrayList<String> MagnetLinkList = new ArrayList<>();
        ArrayList<String> SizeList = new ArrayList<>();
        ArrayList<String> SeedList = new ArrayList<>();
        ArrayList<String> LeechList = new ArrayList<>();
        ArrayList<String> DescriptionList = new ArrayList<>();
        ArrayList<String> PageLinksList = new ArrayList<>();


        TorrentData torrentData = TorrentData.getInstance();

        for (FeedMessage message : feed.getMessages()) {

            if (UsingBackupRSS) {
                NameList.add(message.getTitle() + " - " + message.getSize());
                MagnetLinkList.add(message.getLink());
                DescriptionList.add(removeHashText(message.getDescription()));
            } else {
                NameList.add(message.getTitle() + " - " + message.getSize());
                MagnetLinkList.add(message.getLink());
                SeedList.add(message.getSeeds());
                LeechList.add(message.getLeeches());
                SizeList.add(message.getSize());
                PageLinksList.add(message.getPagelink());
            }
        }


        torrentData.setTorrentNameList(NameList);
        torrentData.setMagnetLinkList(MagnetLinkList);
        torrentData.setTorrentSizeList(SizeList);
        torrentData.setTorrentSeedList(SeedList);
        torrentData.setTorrentLeechList(LeechList);
        torrentData.setTorrentDescriptionList(DescriptionList);
        torrentData.setTorrentPageLinksList(PageLinksList);

    }

    private String removeHashText(String Description) {
        if (UsingBackupRSS) {
            Description = Description.substring(0, Description.length() - 46);
        }
        return Description;
    }

    private void checkAvailability() {

        try {
            final URL url = new URL("http://tf.maxters.net/pbay/search/1080p/0/7/0");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode == 200) {
                UsingBackupRSS = false;
            } else {
                UsingBackupRSS = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

