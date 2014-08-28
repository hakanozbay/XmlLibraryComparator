package com.travelalerter.domain.stations;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.travelalerter.domain.lines.Line;

@Root(name = "Lines")
public class Lines {
	@ElementList(name="Lines", entry = "Line", inline = true)
	private List<Line> lines;

	public Lines(@ElementList(name="Lines", entry = "Line", inline = true) List<Line> lines) {
		this.lines = lines;
	}

	public List<Line> getTubeLines() {
		return lines;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
