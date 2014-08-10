package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class LineStatus {
	@Attribute
	private String ID;
	@Attribute
	private String StatusDetails;

	public String getId() {
		return ID;
	}

	public String getStatusDetails() {
		return StatusDetails;
	}
	
	@Element
	private BranchDisruptions BranchDisruptions;
	
	public BranchDisruptions getBranchDisruptions(){
		return BranchDisruptions;
	}
	
	@Element
	private Line Line;
	
	public Line getLine(){
		return Line;
	}
	
	@Element
	private Status Status;
	
	public Status getStatus(){
		return Status;
	}
	
}
