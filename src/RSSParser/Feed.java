package RSSParser;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {

    private final String title;
    private final String link;
    private final String size;
    private final String description;
    private final String seeds;
    private final String leeches;
    private final String pubDate;

    private final List<FeedMessage> entries = new ArrayList<>();

    public Feed(String title, String link, String size, String description, String seeds,
                String leeches, String pubDate) {
        this.title = title;
        this.link = link;
        this.size = size;
        this.description = description;
        this.seeds = seeds;
        this.leeches = leeches;
        this.pubDate = pubDate;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getSeeds() {
        return seeds;
    }

    public String getLeeches() {
        return leeches;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [leeches=" + leeches + ", description=" + description
                + ", seeds=" + seeds + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

}