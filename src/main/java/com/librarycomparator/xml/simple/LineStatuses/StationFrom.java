package com.librarycomparator.xml.simple.linestatuses;

import org.simpleframework.xml.Attribute;

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
