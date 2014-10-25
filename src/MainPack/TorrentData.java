package MainPack;

import java.util.ArrayList;

/**
 * Created by V on 21/10/2014.
 */
public class TorrentData {

    private ArrayList<String> magnetLinkList;
    private ArrayList<String> torrentNameList;
    private ArrayList<String> torrentSeedList;
    private ArrayList<String> torrentLeechList;
    private ArrayList<String> torrentSizeList;
    private ArrayList<String> torrentDescriptionList;
    private ArrayList<String> TorrentPageLinksList;
    boolean usingFallbackRSSFeed = false;
    private static TorrentData currentInstance = null;


    public ArrayList<String> getTorrentDescriptionList() {
        return torrentDescriptionList;
    }

    public void setTorrentDescriptionList(ArrayList<String> torrentDescriptionList) {
        this.torrentDescriptionList = torrentDescriptionList;
    }

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

    public ArrayList<String> getTorrentNameList() {
        return torrentNameList;
    }

    public void setTorrentNameList(ArrayList<String> torrentNameList) {
        this.torrentNameList = torrentNameList;
    }

    public static TorrentData getInstance() {
        if (currentInstance == null) {
            currentInstance = new TorrentData();
        }

        return currentInstance;
    }


    public ArrayList<String> getTorrentPageLinksList() {
        return TorrentPageLinksList;
    }

    public void setTorrentPageLinksList(ArrayList<String> torrentPageLinksList) {
        TorrentPageLinksList = torrentPageLinksList;
    }
}
