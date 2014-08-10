package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class LineStatus {
	@Attribute
	private String id;
	@Attribute
	private String statusDetails;

	public String getId() {
		return id;
	}

	public String getStatusDetails() {
		return statusDetails;
	}
	
	@ElementList(entry ="BranchDisruptions", inline = true)
	private List<BranchDisruption> branchDiscruptions;
	
	public List<BranchDisruption> getBranchDisruptions(){
		return branchDiscruptions;
	}
	
}
