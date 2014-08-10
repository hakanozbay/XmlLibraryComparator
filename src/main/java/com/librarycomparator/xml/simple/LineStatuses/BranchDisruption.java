package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Element;

public class BranchDisruption {
	@Element
	private StationTo StationTo;
	@Element
	private StationFrom StationFrom;
	@Element 
	private Status Status;
	
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
