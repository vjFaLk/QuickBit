QuickBit
========

Quick and neat torrent searching and downloading tool. 

- Pretty quick
- Search for anything
- Displays Size, Seeds and Peers
- The list of torrents is sorted according to number of seeds, highest having the most number of seeds.
- Directly add torrents to your BitTorrent client (Magnet link) when you click download.
- Or click open to open the webpage for the torrent.
- Autocomplete based on search history
- Lightweight, less than 800 Kb in size
- Standalone, no installation required
- Multiplatform (Except for Mac, will fix later)
- Looks nice too.



Download - http://valmik.in/dl_files/QuickBit.jar


Technical Details 
-------

The application parses custom RSS feeds made by my web app using a custom RSS parser and uses the URISchemeHandler Library to open magnet links in default BitTorrent clients. In the case where the tf.maxters.net RSS feeds are down, it uses the torrentz.eu RSS feeds, which doesn't provide magnet links, so the link provided is parsed for a fastpiratebay.eu link using jSoup, which again is parsed to get the magnet link, which then is opened.


Sources
-----

URISchemeHandler - https://github.com/beothorn/URISchemeHandler
jsoup - http://jsoup.org/
RSS4TPB - https://github.com/vjFaLk/RSS4TPB/


Disclaimer
------
This Software is for legitimate P2P transfers, and users must respect copyright and legal restrictions. I am totally not liable for you being arrested for downloading weird porn, that's totally your thing bro.
