package com.deleon.coco;

import junit.framework.TestCase;

public class CocoTest extends TestCase {

	String version = "0.1";
	Coco c = new Coco();
	
	public void testGetVersion() {
		assert(version.equals(c.getVersion()));
	}

}// class
