package com.travelalerter.domain.stations;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;

public class Station {

	@Attribute
	private String ID;
	@Attribute
	private String Name;
/*
	public Station(String id, String name) {
		this.id = id;
		this.name = name;
	}
	*/
	public String getId() {
		return ID;
	}

	public String getName() {
		return Name;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}