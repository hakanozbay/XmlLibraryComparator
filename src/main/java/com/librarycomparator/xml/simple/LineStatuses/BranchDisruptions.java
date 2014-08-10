package com.librarycomparator.xml.simple.LineStatuses;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class BranchDisruptions {
	@ElementList(name ="BranchDisruption", inline=true, required=false)
	public List<BranchDisruption> branchDisruptions;
	
	public List<BranchDisruption> getBranchDisruptions(){
		return branchDisruptions;
	}
}
