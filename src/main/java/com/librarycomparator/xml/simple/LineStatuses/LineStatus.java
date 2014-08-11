package com.librarycomparator.xml.simple.linestatuses;

import java.util.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class LineStatus {
	@Attribute
	private String ID;
	@Attribute
	private String StatusDetails;
	@Element
	private BranchDisruptions BranchDisruptions;
	@Element
	private Line Line;
	@Element
	private Status Status;
	
	public String getId() {
		return ID;
	}

	public String getStatusDetails() {
		return StatusDetails;
	}
	
	public BranchDisruptions getBranchDisruptions(){
		return BranchDisruptions;
	}
	
	public Line getLine(){
		return Line;
	}
	
	
	public Status getStatus(){
		return Status;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
