package com.travelalerter.tfl.parsers;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.travelalerter.tfl.stationstatuses.StationStatuses;

public class StationStatusParser {
	static Serializer serializer;
	static{
		 serializer = new Persister();		
	}
	
	public static StationStatuses parse(File source) throws Exception{
		return serializer.read(StationStatuses.class, source);
	}
	
	public static StationStatuses parse(String xml) throws Exception {
		return serializer.read(StationStatuses.class, xml);
	}

}
