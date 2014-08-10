package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class BranchDisruptions {
	@ElementList(entry ="BranchDisruption", inline=true, required=false)
	private List<BranchDisruption> branchDisruptions;
	
	public List<BranchDisruption> getBranchDisruptions(){
		return branchDisruptions;
	}
}
