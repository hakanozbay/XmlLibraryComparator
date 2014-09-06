package com.travelalerter.map;

import net.jcip.annotations.Immutable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jgrapht.graph.DefaultEdge;

import com.travelalerter.domain.Stop;
import com.travelalerter.domain.lines.Line;

@Immutable
public class LineEdge extends DefaultEdge {

	private final Line line;
	
	protected LineEdge(Line line) {
		this.line = line;
	}
	
	public Line getLine() {
		return line;
	}
	
	public Stop getSource() {
		return (Stop) super.getSource();
	}
	
	public Stop getTarget() {
		return (Stop) super.getTarget();
	}

	public boolean isSameLine(Stop stop) {
		return line.equals(stop.getLine());
	}

	public boolean isSource(Stop stop) {
		return getSource().equals(stop);
	}

	public boolean isTarget(Stop stop) {
		return getTarget().equals(stop);
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