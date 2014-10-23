QuickBit
========

Quick and mostly redundant torrent searching and downloading tool.

- Search for anything
- The list of torrents is sorted according to number of seeds, highest having the most number of seeds.
- Directly adds torrents to your BitTorrent client (Magnet link) when you click download.


Note - Sometimes the main RSS server is down, so the app uses a fallback server. 
Downside to fallback server is that it doesn't open directly into the BitTorrent client, but opens up a webpage.

Note #2 - I am totally not liable for you being arrested for downloading weird porn, that's totally your thing bro.


Download - http://valmik.in/dl_files/TorrentsDownloader.jar


Technical Details 
-------

The application parses custom RSS feeds made by tf.maxters.net using Rome Utilities Library and uses the URISchemeHandler Library to open magnet links in default BitTorrent clients. In the case where the tf.maxters.net RSS feeds are down, it uses the torrentz.eu RSS feeds, which doesn't provide magnet links, therefore hitting the download button will open up a webpage, where you can download the torrent or get the magnet link.


Sources
-----
Rome Utilities - http://rometools.github.io/rome/

URISchemeHandler - https://github.com/beothorn/URISchemeHandler


Disclaimer
------
This Software is for legitimate P2P transfers, and users must respect copyright and legal restrictions.
