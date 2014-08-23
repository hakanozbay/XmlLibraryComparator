package com.librarycomparator.xml.parser;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.librarycomparator.xml.simple.linestatuses.LineStatuses;

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
