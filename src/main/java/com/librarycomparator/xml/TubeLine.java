package com.librarycomparator.xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class TubeLine {
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Attribute
	public String id;
	@Attribute
	public String name;
	
	@ElementList
	public List<Station> stations;
	
}
