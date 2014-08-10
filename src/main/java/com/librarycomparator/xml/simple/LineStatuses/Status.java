package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.librarycomparator.xml.simple.TubeLine;

@Root
public class Status {
	@Attribute
	public String ID;
	@Attribute
	public String CssClass;
	@Attribute
	public String Description;
	
	@ElementList(entry = "StatusTypes", inline = true)
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
	
	public String getID(){
		return ID;
	}
}
