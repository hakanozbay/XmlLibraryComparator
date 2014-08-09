package com.librarycomparator.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Station {

	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}