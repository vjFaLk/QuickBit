package MainPack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by V on 24/10/2014.
 */
public class HTMLParser {

    public void parseLink(String pageLink) {
        Document doc = null;
        try {
            doc = Jsoup.connect(pageLink).get();


            Elements links = doc.getElementsByTag("a");
            for (Element link : links) {
                if (link.text().contains("fastpiratebay.eu")) {
                    parseForMagnetLink(link.attr("href"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseForMagnetLink(String pageLink) {
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
