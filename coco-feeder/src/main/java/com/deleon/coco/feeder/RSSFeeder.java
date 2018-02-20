package com.deleon.coco.feeder;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSFeeder extends Feeder {

	private static final Logger logger = LogManager.getLogger(RSSFeeder.class);

	protected String url;
	// protected Feed feed;
	protected ArrayList<FeedItem> feedItems;

	public RSSFeeder(HashMap<String, String> properties, String logURI) {
		this.properties = properties;
		this.logURI = logURI;
		this.feedItems = new ArrayList<FeedItem>();
	}

	@Override
	public boolean init() {
		try {
			if (this.properties.get("URL") == null || this.logURI == null)
				throw new Exception("Incorrect Initialization Parameters!");

			LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
			File file = new File(this.logURI);
			if (!file.exists())
				throw new Exception("Logger Configuration File does not exist: " + file.toURI());
			context.setConfigLocation(file.toURI());
			this.url = properties.get("URL");
			logger.debug("Logger initializated ok");
		} // try
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}// init

	@SuppressWarnings("rawtypes")
	@Override
	public boolean execute() {
		logger.debug("Entering RSSFeeder for: " + url);
		try {
			URL url = new URL(this.url);
			
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(httpcon));
			List entries = feed.getEntries();
			Iterator itEntries = entries.iterator();
			FeedItem feedItem = new FeedItem();
			 while (itEntries.hasNext()) {
		            SyndEntry entry = (SyndEntry) itEntries.next();
		            feedItem.setHtmlDescription(entry.getDescription().getValue());
		            feedItem.setPlainDescription(entry.getDescription().getValue());
		            feedItem.setTitle(entry.getTitle());
		            feedItem.setLink(entry.getLink());
		            feedItem.setPublicationDate(entry.getPublishedDate());
		            this.feedItems.add(feedItem);
		        }
		} catch (MalformedURLException e) {
			logger.error("Error with the URL Provided: " + this.url, e);
			return false;
		} catch (IOException e) {
			logger.error("Error reading the URL Provided: " + this.url, e);
			return false;
		} catch (IllegalArgumentException e) {
			logger.error("Error with the parameters: " + this.url, e);
			return false;
		} catch (FeedException e) {
			logger.error("Error with the URL Feed: " + this.url, e);
			return false;
		}
		logger.debug("Items read: " + feedItems.size());
		return true;
	}// execute

	@Override
	public boolean persist() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<FeedItem> getFeedItems() {
		return feedItems;
	}

	public void setFeedItems(ArrayList<FeedItem> feedItems) {
		this.feedItems = feedItems;
	}

}// class
