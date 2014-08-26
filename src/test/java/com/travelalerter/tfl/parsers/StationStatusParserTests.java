package com.travelalerter.tfl.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.travelalerter.tfl.stationstatuses.StationStatuses;

public class StationStatusParserTests {

	File _source;

	@Before
	public void setUp() throws Exception {
		_source = new File(ClassLoader.getSystemResource("tfl/StationStatusesv2.xml")
				.toURI());
	}
	
	@Test
	public void ParsingSampleXmlShouldReturnSevenStationStationStatuses() throws Exception{
		StationStatuses stationStatuses = StationStatusParser.parse(_source);
		assertNotNull(stationStatuses);
		assertEquals(9, stationStatuses.getStationStatuses().size());
	}

	
	
}
