package com.librarycomparator.xml.xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value="line")
public class TubeLine {

	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String name;

	@XStreamImplicit(itemFieldName="station")
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
