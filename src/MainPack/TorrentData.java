package MainPack;

import java.util.ArrayList;

/**
 * Created by V on 21/10/2014.
 */
public class TorrentData {

    private ArrayList<String> magnetLinkList;
    private ArrayList<String> torrentNameList;
    private ArrayList<String> torrentDescriptionList;
    private ArrayList<String> TorrentPageLinksList;
    private static TorrentData currentInstance = null;


    public ArrayList<String> getTorrentDescriptionList() {
        return torrentDescriptionList;
    }

    public void setTorrentDescriptionList(ArrayList<String> torrentDescriptionList) {
        this.torrentDescriptionList = torrentDescriptionList;
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
