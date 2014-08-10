package com.librarycomparator.xml.simple.LineStatuses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class BranchDisruption {
	@Element(name = "StationTo")
	public Station stationTo;
	@Element(name = "StationFrom")
	public Station stationFrom;
	
	public Station getStationTo(){
		return stationTo;
	}
	
	public Station getStationFrom(){
		return stationFrom;
	}
	
}
