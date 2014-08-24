package com.travelalerter.tfl.stationstatuses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.travelalerter.domain.stations.Station;

public class StationStatus {
	@Attribute
	private String ID;
	@Attribute
	private String StatusDetails;
	
	@Element 
	Station Station;
	@Element 
	Status Status;
	
	public String getId() {
		return ID;
	}

	public String getStatusDetails() {
		return StatusDetails;
	}
	
	public Station getStation(){
		return Station;
	}
	
	public Status getStatus(){
		return Status;
	}
	
	
}
