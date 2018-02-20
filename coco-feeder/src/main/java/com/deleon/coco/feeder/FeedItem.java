package com.deleon.coco.feeder;

import java.util.Date;

public class FeedItem {

	private String title;
	private String link;
	private String plainDescription;
	private String htmlDescription;
	private Date publicationDate;
	public String getTitle() {
		return title;
	}
	
	public FeedItem() {
		
	}
	
	public FeedItem(String title, String link, String plainDescription, String htmlDescription,
			Date publicationDate) {
		super();
		this.title = title;
		this.link = link;
		this.plainDescription = plainDescription;
		this.htmlDescription = htmlDescription;
		this.publicationDate = publicationDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPlainDescription() {
		return plainDescription;
	}
	public void setPlainDescription(String plainDescription) {
		this.plainDescription = plainDescription;
	}
	public String getHtmlDescription() {
		return htmlDescription;
	}
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date date) {
		this.publicationDate = date;
	}

	@Override
	public String toString() {
		return "FeedItem [title=" + title + ", link=" + link + ", plainDescription=" + plainDescription
				+ ", htmlDescription=" + htmlDescription + ", publicationDate=" + publicationDate + "]";
	}
	
}// class
