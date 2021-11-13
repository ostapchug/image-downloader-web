package com.example.imagedownloaderjsp.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	
	public List<String> getImageURLs(String url) throws IOException{
		List<String> imageURLs = new ArrayList<>();
		
		// The connect() method creates a new connection, and get() fetches and parses a HTML file
        Document doc = Jsoup.connect(url).get();
        
        // Returns a list of matching elements 
        Elements links = doc.select("a[href]");
        Elements imports = doc.select("link[href]");
        Elements images = doc.select("img[src]");
        
        // Extract attributes from elements
        // To get an absolute URL, there is a attribute key prefix abs:
        // that will cause the attribute value to be resolved against the document's base URI 
        for (Element el: links) {
            String imageUrl = el.attr("abs:href");
            if(imageUrl.toLowerCase().endsWith(".jpg")||imageUrl.toLowerCase().endsWith(".png")) {
            	imageURLs.add(imageUrl);
            }
        }
        
        for (Element el: imports) {
            String imageUrl = el.attr("abs:href");
            if(imageUrl.toLowerCase().endsWith(".jpg")||imageUrl.toLowerCase().endsWith(".png")) {
            	imageURLs.add(imageUrl);
            }
        }
        
        for (Element el: images) {
            String imageUrl = el.attr("abs:src");
            imageURLs.add(imageUrl);
        }
				
		return imageURLs;
	}

}
