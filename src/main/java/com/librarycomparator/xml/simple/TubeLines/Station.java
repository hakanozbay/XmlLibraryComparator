package com.librarycomparator.xml.simple.tubelines;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}