package com.librarycomparator.xml;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.librarycomparator.xml.simple.LineStatuses.LineStatuses;

public class LineStatusParser {
	Serializer serializer;
	public LineStatusParser(){
		 serializer = new Persister();		
	}
	
	public LineStatuses Parse(File source) throws Exception{
		return serializer.read(LineStatuses.class, source);
	}

}
