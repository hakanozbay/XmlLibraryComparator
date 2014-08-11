package com.librarycomparator.xml.simple.linestatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

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
}
