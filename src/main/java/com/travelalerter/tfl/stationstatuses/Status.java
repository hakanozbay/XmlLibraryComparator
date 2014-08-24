package com.travelalerter.tfl.stationstatuses;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.travelalerter.tfl.stationstatuses.StatusType;

public class Status {
	@Attribute
	private String ID;
	@Attribute
	private String CssClass;
	@Attribute
	private String Description;
	@Attribute
	private String IsActive;
	
	@Element
	private StatusType StatusType;

	public StatusType getStatusType() {
		return StatusType;
	}
	
	public String getDescription(){
		return Description;
	}
	
	public String getCssClass(){
		return CssClass;
	}
	
	public String getIsActive(){
		return IsActive;
	}
	
	public String getID(){
		return ID;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}