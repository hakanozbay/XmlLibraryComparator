package com.travelalerter.domain.lines;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
