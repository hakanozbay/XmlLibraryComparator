package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Status {
	@Attribute
	public String ID;
	@Attribute
	public String CssClass;
	@Attribute
	public String Description;
	@Attribute
	private String IsActive;
	
	@ElementList(entry = "StatusType", inline = true)
	private List<StatusType> statusTypes;

	public List<StatusType> getStatusTypes() {
		return statusTypes;
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
