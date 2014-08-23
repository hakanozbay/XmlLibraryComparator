package com.travelalerter.tfl.linestatuses;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.ElementList;

public class BranchDisruptions {
	@ElementList(entry ="BranchDisruption", inline=true, required=false)
	private List<BranchDisruption> branchDisruptions;
	
	public List<BranchDisruption> getBranchDisruptions(){
		return branchDisruptions;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
