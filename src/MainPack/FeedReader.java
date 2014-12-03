package MainPack;

import RSSParser.Feed;
import RSSParser.FeedMessage;
import RSSParser.RSSFeedParser;

import java.util.ArrayList;


class FeedReader {

    private boolean UsingBackupRSS = false;


    public Feed getFeed(String torrentName) {
        RSSFeedParser parser;
        Feed feed = null;
        torrentName = torrentName.replace(' ', '+');
        try {
                parser = new RSSFeedParser("http://adept-bastion-742.appspot.com/" + torrentName);
                feed = parser.readFeed();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return feed;

    }

    public void createTorrentDataLists(Feed feed) {


        ArrayList<String> NameList = new ArrayList<>();
        ArrayList<String> MagnetLinkList = new ArrayList<>();
        ArrayList<String> DescriptionList = new ArrayList<>();
        ArrayList<String> PageLinksList = new ArrayList<>();


        TorrentData torrentData = TorrentData.getInstance();

        for (FeedMessage message : feed.getMessages()) {

                NameList.add(message.getTitle() + " - " + getSizeFromDescription(message.getDescription()));
                MagnetLinkList.add(message.getLink());
                PageLinksList.add(message.getPagelink());
                DescriptionList.add(message.getDescription());
            }

        torrentData.setTorrentNameList(NameList);
        torrentData.setMagnetLinkList(MagnetLinkList);
        torrentData.setTorrentDescriptionList(DescriptionList);
        torrentData.setTorrentPageLinksList(PageLinksList);

    }

    private String getSizeFromDescription(String Description) {

        String size = "";
        for (int i = 0; i < Description.length(); i++) {
            size += Description.charAt(i);
            if (Description.charAt(i) == 'B') {
                break;
            }
        }
        size = size.substring(5, size.length());
        return size;

    }


//    private void checkAvailability() {
//
//        try {
//            final URL url = new URL("http://adept-bastion-742.appspot.com/test");
//            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
//            int responseCode = huc.getResponseCode();
//            UsingBackupRSS = responseCode != 200;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}

