package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;

public class StatusType {
	@Attribute
	private String ID;
	@Attribute
	private String Description;
	
	public String getDescription(){
		return Description;
	}
	
	public String getID(){
		return ID;
	}
}
