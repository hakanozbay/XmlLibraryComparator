package com.librarycomparator.xml;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.librarycomparator.xml.simple.TubeLines.TubeLines;

public class TubeLinesParser {

	private TubeLines _tubeLines;

	public TubeLinesParser(File source) throws Exception {
		Serializer serializer = new Persister();		
		_tubeLines = serializer.read(TubeLines.class, source);
	}
	
	public TubeLines GetTubeLines(){
		return _tubeLines;
	}

}
