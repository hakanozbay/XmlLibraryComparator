package com.librarycomparator.xml.simple;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.librarycomparator.xml.parser.StationStatusParser;
import com.librarycomparator.xml.simple.stationstatuses.StationStatuses;

public class StationStatusParserTests {

	File _source;

	@Before
	public void setUp() throws Exception {
		_source = new File(ClassLoader.getSystemResource("SationStatuses.xml")
				.toURI());
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void ParsingSampleXmlShouldReturnSevenStationStationStatuses() throws Exception{
		StationStatuses stationStatuses = StationStatusParser.parse(_source);
		assertNotNull(stationStatuses);
		assertEquals(7, stationStatuses.getStationStatuses().size());
	}

	
	
}
