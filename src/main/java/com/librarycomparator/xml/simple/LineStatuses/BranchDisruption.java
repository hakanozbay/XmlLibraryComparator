package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name ="BranchDisruption")
public class BranchDisruption {
	@Element
	public StationTo StationTo;
	@Element
	public StationFrom StationFrom;
	@Element Status Status;
	
	public Status getStatus(){
		return Status;
	}
	
	public StationTo getStationTo(){
		return StationTo;
	}
	
	public StationFrom getStationFrom(){
		return StationFrom;
	}
	
}
