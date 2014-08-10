package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
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
