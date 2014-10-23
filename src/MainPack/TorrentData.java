package MainPack;

import java.util.ArrayList;

/**
 * Created by V on 21/10/2014.
 */
public class TorrentData {

    private ArrayList<String> magnetLinkList;
    private ArrayList<String> torrentLinkList;
    private ArrayList<String> torrentSeedList;
    private ArrayList<String> torrentLeechList;
    private ArrayList<String> torrentSizeList;

    public ArrayList<String> getTorrentSeedList() {
        return torrentSeedList;
    }

    public void setTorrentSeedList(ArrayList<String> torrentSeedList) {
        this.torrentSeedList = torrentSeedList;
    }

    public ArrayList<String> getTorrentLeechList() {
        return torrentLeechList;
    }

    public void setTorrentLeechList(ArrayList<String> torrentLeechList) {
        this.torrentLeechList = torrentLeechList;
    }

    public ArrayList<String> getTorrentSizeList() {
        return torrentSizeList;
    }

    public void setTorrentSizeList(ArrayList<String> torrentSizeList) {
        this.torrentSizeList = torrentSizeList;
    }


    boolean usingFallbackRSSFeed = false;
    private static TorrentData currentInstance = null;


    public boolean isUsingFallbackRSSFeed() {
        return usingFallbackRSSFeed;
    }


    public void setUsingFallbackRSSFeed(boolean usingFallbackRSSFeed) {
        this.usingFallbackRSSFeed = usingFallbackRSSFeed;
    }




    public ArrayList<String> getMagnetLinkList() {
        return magnetLinkList;
    }

    public void setMagnetLinkList(ArrayList<String> magnetLinkList) {
        this.magnetLinkList = magnetLinkList;
    }

    public ArrayList<String> getTorrentLinkList() {
        return torrentLinkList;
    }

    public void setTorrentLinkList(ArrayList<String> torrentLinkList) {
        this.torrentLinkList = torrentLinkList;
    }

    public static TorrentData getInstance() {
        if (currentInstance == null) {
            currentInstance = new TorrentData();
        }

        return currentInstance;
    }


}
