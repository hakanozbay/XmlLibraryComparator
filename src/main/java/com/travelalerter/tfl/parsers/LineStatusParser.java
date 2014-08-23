package com.travelalerter.tfl.parsers;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.travelalerter.tfl.linestatuses.LineStatuses;

public class LineStatusParser {
	static Serializer serializer;
	static{
		 serializer = new Persister();		
	}
	
	public static LineStatuses parse(File source) throws Exception{
		return serializer.read(LineStatuses.class, source);
	}
	
	public static LineStatuses parse(String xml) throws Exception {
		return serializer.read(LineStatuses.class, xml);
	}

}
