package MainPack;

import java.util.ArrayList;

/**
 * Created by V on 21/10/2014.
 */
public class TorrentData {

    private ArrayList<String> torrentLinkList, magnetLinkList;
    private static TorrentData currentInstance = null;

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
