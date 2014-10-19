package MainPack;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.Iterator;
import java.util.List;


public class FeedReader {

    private String movieName;
    private int movieQuality;
    private SyndFeed feed = null;

    public FeedReader(String movieName, int movieQuality) {
        this.movieName = movieName;
        this.movieQuality = movieQuality;
        readPrint();
    }

    public SyndFeed readPrint() {
        boolean ok = false;
        SyndFeedInput input = new SyndFeedInput();


        try {
            URL feedUrl = new URL("https://yts.re/rss/" + movieName + "/"+movieQuality+"p/All/0");

            feed = input.build(new XmlReader(feedUrl));
            ok = true;


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }


        if (!ok) {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }

        return feed;

    }

    public void printTitle() {

        for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
            SyndEntry entry = (SyndEntry) i.next();
            System.out.println(entry.getTitle());
        }

    }


    }

