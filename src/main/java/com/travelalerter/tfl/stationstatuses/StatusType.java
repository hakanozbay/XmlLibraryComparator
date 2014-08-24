package com.travelalerter.tfl.stationstatuses;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
