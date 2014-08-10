package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;

public class Line {
	@Attribute
	private String ID;
	@Attribute
	private String Name;

	public String getId() {
		return ID;
	}

	public String getName() {
		return Name;
	}
}
