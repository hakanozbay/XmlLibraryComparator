package com.travelalerter.domain.lines;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "Lines")
public class Lines {

	@ElementList(entry = "Line", inline = true)
	private List<Line> Lines;

	public List<Line> getTubeLines() {
		return Lines;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
