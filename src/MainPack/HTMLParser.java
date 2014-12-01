package MainPack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by V on 24/10/2014.
 */
class HTMLParser {

    public String parseLink(String pageLink) {
        Document doc = null;
        String magnetLink = null;
        try {
            doc = Jsoup.connect(pageLink).get();


            Elements links = doc.getElementsByTag("a");
            for (Element link : links) {
                if (link.text().contains("fastpiratebay")) {
                    magnetLink = link.attr("href");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return magnetLink;
    }

    public void parseForMagnetLink(String pageLink) {
        Document doc = null;
        try {
            doc = Jsoup.connect(pageLink).get();


            Elements links = doc.getElementsByTag("a");
            for (Element link : links) {
                if (link.attr("href").contains("magnet:")) {
                    LinkHandler openLink = new LinkHandler();
                    openLink.openMagnetLink(link.attr("href"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
