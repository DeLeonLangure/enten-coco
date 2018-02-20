package com.deleon.coco;

import java.util.HashMap;

import com.deleon.coco.feeder.RSSFeeder;

import junit.framework.TestCase;

public class RSSFeederTest extends TestCase {

	HashMap<String, String> properties = new HashMap<String, String>();
	String logURI;
	RSSFeeder r;

	protected void setUp() throws Exception {
		super.setUp();
		properties.put("URL", "http://www.eluniversal.com.mx/rss.xml");
		logURI = "/Users/Alex/javas/coco/coco/Enten-Coco/global-resources/log4j.xml";
		r = new RSSFeeder(properties, logURI);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInit() {
		r = new RSSFeeder(properties, logURI);
		assert (r.init());
	}
	
	public void testExecute() {
		r = new RSSFeeder(properties, logURI);
		r.init();
		assert(r.execute());
		assert(!r.getFeedItems().isEmpty());
		assert(r.getFeedItems().size() == 10);
	}
}
