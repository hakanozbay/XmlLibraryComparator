package com.librarycomparator.xml.simple.stationstatuses;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="ArrayOfStationStatuses")
public class StationStatuses {

	@ElementList(entry = "StationStatus", inline = true)
	private List<StationStatus> _stationStatuses;

	public List<StationStatus> getStationStatuses() {
		return _stationStatuses;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
