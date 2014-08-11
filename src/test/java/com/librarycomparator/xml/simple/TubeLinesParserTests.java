package com.librarycomparator.xml.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.librarycomparator.xml.TubeLinesParser;
import com.librarycomparator.xml.simple.tubelines.Station;
import com.librarycomparator.xml.simple.tubelines.TubeLine;
import com.librarycomparator.xml.simple.tubelines.TubeLines;

public class TubeLinesParserTests {

	private TubeLines _tubeLines;
	
	@Before
	public void setUp() throws Exception {
		File source = new File(ClassLoader.getSystemResource("TubeLines.xml").toURI());
		TubeLinesParser tubeLinesParser = new TubeLinesParser(source);
		_tubeLines = tubeLinesParser.GetTubeLines();
		
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
		TubeLine waterlooAndCityLine = _tubeLines.getTubeLines().get(0);
		assertNotNull(waterlooAndCityLine);
		assertTrue(waterlooAndCityLine.getId().equalsIgnoreCase("12"));
		assertTrue(waterlooAndCityLine.getName().equals("Waterloo and City"));
		assertFalse(waterlooAndCityLine.getStations().isEmpty());

	}

	@Test
	public void GetWaterlooAndCityLineStationsTest() {
		
		TubeLine waterlooAndCityLine = GetTubeLine("Waterloo and City", _tubeLines.getTubeLines());
		List<Station> stations = waterlooAndCityLine.getStations();
		assertTrue(stations.get(0).getId().equalsIgnoreCase("252"));
		assertTrue(stations.get(0).getName().equals("Waterloo"));

		assertTrue(stations.get(1).getId().equals("12"));
		assertTrue(stations.get(1).getName().equals("Bank"));
	}
	
	private TubeLine GetTubeLine(String tubeLineName, List<TubeLine> tubeLines){
		
		for(TubeLine tubeLine : tubeLines){
			if(tubeLine.getName().equals(tubeLineName))
				return tubeLine;
		}
		
		return null;
	}
}
