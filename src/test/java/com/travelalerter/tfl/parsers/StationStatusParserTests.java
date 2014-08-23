package com.travelalerter.tfl.parsers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.travelalerter.tfl.parsers.StationStatusParser;
import com.travelalerter.tfl.stationstatuses.StationStatuses;

public class StationStatusParserTests {

	File _source;

	@BeforeClass
	public void setUp() throws Exception {
		_source = new File(ClassLoader.getSystemResource("tfl/StationStatuses.xml")
				.toURI());
	}
	
	@Test
	public void ParsingSampleXmlShouldReturnSevenStationStationStatuses() throws Exception{
		StationStatuses stationStatuses = StationStatusParser.parse(_source);
		assertNotNull(stationStatuses);
		assertEquals(7, stationStatuses.getStationStatuses().size());
	}

	
	
}
