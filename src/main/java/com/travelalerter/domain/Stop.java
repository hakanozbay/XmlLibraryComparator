package com.travelalerter.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.jcip.annotations.Immutable;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.travelalerter.domain.lines.Line;
import com.travelalerter.domain.stations.Station;

/**
 * A tube Stop consists of a station and a line.
 * This is a flyweight class and you can retrieve all instances of stops give a station and a line using the Stop#get method.
 *
 * Created by Jim on 02/09/2014.
 */
@Immutable
public class Stop {

	private static final ConcurrentMap<MultiKey<Object>, Stop> cache = new ConcurrentHashMap<MultiKey<Object>, Stop>();
	private static final String[] EXCLUDE_FIELDS = new String[] {"cache"};

	private final Station station;
	private final Line line;

	protected Stop(Station station, Line line) {
		this.station = station;
		this.line = line;
	}

	public Station getStation() {
		return station;
	}

	public Line getLine() {
		return line;
	}

	public boolean isSameLine(Stop other) {
		return line.equals(other.getLine());
	}

	public boolean isSameStation(Stop other) {
		return station.equals(other.getStation());
	}

	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this, EXCLUDE_FIELDS);
	}

	@Override
	public boolean equals(Object other)
	{
		return EqualsBuilder.reflectionEquals(this, other, EXCLUDE_FIELDS);
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static Stop retrieve(Station station, Line line) {
		MultiKey<Object> key = makeKey(station, line);
		cache(key, station, line);
		return cache.get(key);
	}

	private static void cache(MultiKey<Object> key, Station station, Line line) {
		if (!cache.containsKey(key)) {
			Stop newStop = new Stop(station, line);
			cache.putIfAbsent(key, newStop);
		}
	}

	private static MultiKey<Object> makeKey(Station station, Line line) {
		return new MultiKey<Object>(station, line);
	}

}
