package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class StationFrom {
	@Attribute
	private String ID;
	@Attribute
	private String Name;
	
	public String getName(){
		return Name;
	}
	
	public String getID(){
		return ID;		
	}
}
