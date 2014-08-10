package com.librarycomparator.xml.simple.TubeLines;

import org.simpleframework.xml.Attribute;

public class Station {

	@Attribute
	private String id;
	@Attribute
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}