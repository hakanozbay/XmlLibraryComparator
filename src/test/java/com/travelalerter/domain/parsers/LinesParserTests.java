package com.travelalerter.domain.parsers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travelalerter.domain.lines.Line;
import com.travelalerter.domain.lines.Lines;
import com.travelalerter.tfl.parsers.TubeLinesParser;

public class LinesParserTests {

	private Lines _tubeLines;
	
	@Before
	public void setUp() throws Exception {
		File source = new File(ClassLoader.getSystemResource("domain/Lines.xml").toURI());
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
		Line waterlooAndCityLine = GetTubeLine("Waterloo and City", _tubeLines.getTubeLines());
		assertNotNull(waterlooAndCityLine);
		assertTrue(waterlooAndCityLine.getId().equalsIgnoreCase("12"));
		assertTrue(waterlooAndCityLine.getName().equals("Waterloo and City"));

	}

	private Line GetTubeLine(String tubeLineName, List<Line> tubeLines){
		
		for(Line tubeLine : tubeLines){
			if(tubeLine.getName().equals(tubeLineName))
				return tubeLine;
		}
		
		return null;
	}
}
