package com.librarycomparator.xml;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import static org.junit.Assert.*;

public class TubeLinesParserTests {

	private TubeLines _tubeLines;
	
	@Before
	public void setUp() throws Exception {
		Serializer serializer = new Persister();
		File source = new File(ClassLoader.getSystemResource("TubeLines.xml")
				.toURI());
		_tubeLines = serializer.read(TubeLines.class, source);
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

	@Test
	public void GetWaterlooAndCityLineTest() {
		TubeLine tubeLine = _tubeLines.getTubeLines().get(0);

		assertTrue(tubeLine.getId().equalsIgnoreCase("12"));
		assertTrue(tubeLine.getName().equalsIgnoreCase("waterloo and city"));
		assertFalse(tubeLine.getStations().isEmpty());

	}

	@Test
	public void GetWaterlooAndCityLineStationsTest() {
		TubeLine tubeLine = _tubeLines.getTubeLines().get(0);

		List<Station> stations = tubeLine.getStations();
		assertTrue(stations.get(0).getId().equalsIgnoreCase("252"));
		assertTrue(stations.get(0).getName().equalsIgnoreCase("waterloo"));

		assertTrue(stations.get(1).getId().equalsIgnoreCase("12"));
		assertTrue(stations.get(1).getName().equalsIgnoreCase("bank"));
	}

}
