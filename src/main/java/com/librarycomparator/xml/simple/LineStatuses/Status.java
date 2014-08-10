package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Status {
	@Attribute
	public String ID;
	
	public String getID(){
		return ID;
	}
}
