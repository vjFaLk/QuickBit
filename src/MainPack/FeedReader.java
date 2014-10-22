package MainPack;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class FeedReader {

    private String torrentName;
    private SyndFeed feed = null;
    private ArrayList<String> magnetLinkList, torrentNameList;


    public FeedReader(String torrentName) {
        this.torrentName = torrentName;
        readPrint();
    }

    public SyndFeed readPrint() {
        SyndFeedInput input = new SyndFeedInput();
        TorrentData torrentData = TorrentData.getInstance();

        try {
            if (checkAvailability()) {
                URL feedUrl = new URL("http://tf.maxters.net/pbay/search/" + torrentName + "/0/7/0");
                feed = input.build(new XmlReader(feedUrl));
            } else {
                URL feedUrl = new URL("http://torrentz.eu/feed?q=" + torrentName);
                feed = input.build(new XmlReader(feedUrl));
                torrentData.setUsingFallbackRSSFeed(true);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return feed;

    }

    public ArrayList<String> createTorrentList() {

        torrentNameList = new ArrayList<String>();
        magnetLinkList = new ArrayList<String>();
        TorrentData torrentData = TorrentData.getInstance();

        for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
            SyndEntry entry = (SyndEntry) i.next();
            torrentNameList.add(entry.getTitle());
            magnetLinkList.add(entry.getLink());
        }

        torrentData.setTorrentLinkList(torrentNameList);
        torrentData.setMagnetLinkList(magnetLinkList);

        return torrentNameList;
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

