package com.librarycomparator.xml;

import java.io.File;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class TubeLinesParserTests {

	@Test
	public void GetListOfTubeLinesTest() throws Exception {
		Serializer serializer = new Persister();
		//TubeLinesParserTests.class.getResource("TubeLines.xml");
		File source = new File("src/test/resources/TubeLines.xml");

		TubeLines tubeLines = serializer.read(TubeLines.class, source);
	}

}
