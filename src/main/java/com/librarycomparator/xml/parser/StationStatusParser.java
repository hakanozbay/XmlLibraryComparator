package com.librarycomparator.xml.parser;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.librarycomparator.xml.simple.stationstatuses.StationStatuses;

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
