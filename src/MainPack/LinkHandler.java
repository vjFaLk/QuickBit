package MainPack;

import uriSchemeHandler.CouldNotOpenUriSchemeHandler;
import uriSchemeHandler.URISchemeHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by V on 21/10/2014.
 */
public class LinkHandler {


    public void openMagnetLink(String magnetLink) {
        URI magnetLinkUri = null;
        magnetLink = magnetLink.substring(0, magnetLink.length() - 4);
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
}


