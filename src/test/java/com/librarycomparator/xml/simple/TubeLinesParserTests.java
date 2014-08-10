package com.librarycomparator.xml.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.librarycomparator.xml.LineStatusParser;
import com.librarycomparator.xml.TubeLinesParser;

public class TubeLinesParserTests {

	private TubeLines _tubeLines;
	
	@Before
	public void setUp() throws Exception {
		File source = new File(ClassLoader.getSystemResource("LineStatus.xml")
				.toURI());
		LineStatusParser lineStatusParser = new LineStatusParser();
		
	}

	@After
	public void tearDown() {
		_tubeLines = null;
	}

	@Test
	public void GetListOfTubeLinesIsNotEmptyTest() {

		assertNotNull(_tubeLines);
		assertFalse(_tubeLines.getTubeLines().isEmpty());

	}


}
