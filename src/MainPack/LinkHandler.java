package MainPack;

import uriSchemeHandler.CouldNotOpenUriSchemeHandler;
import uriSchemeHandler.URISchemeHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by V on 21/10/2014.
 */
class LinkHandler {


    public void openMagnetLink(String magnetLink) {
        URI magnetLinkUri = null;
        try {
            magnetLinkUri = new URI(magnetLink);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        URISchemeHandler uriSchemeHandler = new URISchemeHandler();
        try {
            uriSchemeHandler.open(magnetLinkUri);
        } catch (CouldNotOpenUriSchemeHandler couldNotOpenUriSchemeHandler) {
            couldNotOpenUriSchemeHandler.printStackTrace();
        }
    }

    public void openWebLink(String webPageLink) {
        URI link = null;
        webPageLink = webPageLink.trim();
        try {
            link = new URI(webPageLink);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            java.awt.Desktop.getDesktop().browse(link);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Using a proxy to display PirateBay page for people who cannot access the website directly.
    public String addSuffix(String pageLink) {
        pageLink = pageLink.substring(9, pageLink.length());
        pageLink = "http://fastpiratebay.eu" + pageLink;
        return pageLink;
    }
}


