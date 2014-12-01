package RSSParser;

/**
 * Created by V on 23/10/2014.
 */
public class FeedMessage {

    /*
     * Represents one RSS message
     */

    private String title;
    private String description;
    private String link;
    private String size;
    private String pagelink;
    private String guid;

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    private String seeds;

    public String getLeeches() {
        return leeches;
    }

    public void setLeeches(String leeches) {
        this.leeches = leeches;
    }

    private String leeches;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPagelink() {
        return pagelink;
    }

    public void setPagelink(String pagelink) {
        this.pagelink = pagelink;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", pagelink=" + pagelink + ", guid=" + guid
                + "]";
    }

}

