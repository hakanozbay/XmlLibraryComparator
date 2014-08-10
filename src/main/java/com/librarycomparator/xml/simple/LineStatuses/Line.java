package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
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
