package com.librarycomparator.xml;

import org.simpleframework.xml.Attribute;

public class Station {
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Attribute
	public String id;
	@Attribute
	public String name;
}
