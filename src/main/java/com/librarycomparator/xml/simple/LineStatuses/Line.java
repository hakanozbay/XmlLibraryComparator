package com.librarycomparator.xml.simple.linestatuses;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
