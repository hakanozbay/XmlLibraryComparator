package com.librarycomparator.xml.simple;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class TubeLine {

	@Attribute
	private String id;
	@Attribute
	private String name;

	@ElementList(inline = true)
	private List<Station> stations;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Station> getStations() {
		return stations;
	}

}
