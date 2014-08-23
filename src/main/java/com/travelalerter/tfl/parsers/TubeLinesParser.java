package com.travelalerter.tfl.parsers;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.travelalerter.domain.lines.Lines;

public class TubeLinesParser {

	private Lines _tubeLines;

	public TubeLinesParser(File source) throws Exception {
		Serializer serializer = new Persister();		
		_tubeLines = serializer.read(Lines.class, source);
	}
	
	public Lines GetTubeLines(){
		return _tubeLines;
	}

}
