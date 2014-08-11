package com.librarycomparator.xml.simple.linestatuses;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;

public class StationTo {
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
