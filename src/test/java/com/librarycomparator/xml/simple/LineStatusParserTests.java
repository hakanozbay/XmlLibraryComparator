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
import com.librarycomparator.xml.simple.LineStatuses.LineStatuses;

public class LineStatusParserTests {

	LineStatusParser _lineStatusParser;
	File _source;
	
	@Before
	public void setUp() throws Exception {
		 _source = new File(ClassLoader.getSystemResource("LineStatuses.xml")
				.toURI());
		 _lineStatusParser = new LineStatusParser();

	}

	@After
	public void tearDown() {
		_lineStatusParser = null;
	}

	@Test
	public void GetListOfLineStatusesIsNotEmptyTest() throws Exception {
		LineStatuses lineStatuses 	= _lineStatusParser.Parse(_source);
		assertNotNull(lineStatuses);
		assertFalse(lineStatuses.getLineStatuses().isEmpty());

	}	

}
