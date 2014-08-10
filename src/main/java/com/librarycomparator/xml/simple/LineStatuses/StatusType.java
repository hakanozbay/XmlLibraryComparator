package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class StatusType {
	@Attribute
	public String ID;
	@Attribute
	public String Description;
	
	public String getDescription(){
		return Description;
	}
	
	public String getID(){
		return ID;
	}
}
