package com.deleon.coco.feeder;

import java.util.HashMap;

public abstract class Feeder {

	protected HashMap<String, String> properties = new HashMap<String, String>();
	protected int type;
	protected String logConfigFileURI;
	
	public abstract boolean init();
	public abstract boolean execute();
	public abstract boolean persist();
	
}//class
