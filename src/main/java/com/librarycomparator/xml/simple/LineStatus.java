package com.librarycomparator.xml.simple;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class LineStatus {
	@Attribute
	private String id;
	@Attribute
	private String statusDetails;

	public String getId() {
		return id;
	}

	public String getStatusDetails() {
		return statusDetails;
	}

	
}
