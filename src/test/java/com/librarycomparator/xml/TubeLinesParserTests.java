package com.librarycomparator.xml;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import static org.junit.Assert.*;

public class TubeLinesParserTests {

	@Test
	public void GetListOfTubeLinesTest() throws Exception {
		
		Serializer serializer = new Persister();
		File source = new File(ClassLoader.getSystemResource("TubeLines.xml").toURI());

		TubeLines tubeLines = serializer.read(TubeLines.class, source);
		
		assertNotNull(tubeLines);
		assertFalse(tubeLines.getTubeLines().isEmpty());
		
		TubeLine tubeLine = tubeLines.getTubeLines().get(0);
		
		assertTrue(tubeLine.getId().equalsIgnoreCase("12"));
		assertTrue(tubeLine.getName().equalsIgnoreCase("waterloo and city"));
		
		assertFalse(tubeLine.getStations().isEmpty());
		
		List<Station> stations = tubeLine.getStations(); 
		assertTrue(stations.get(0).getId().equalsIgnoreCase("252"));
		assertTrue(stations.get(0).getName().equalsIgnoreCase("waterloo"));
		
		assertTrue(stations.get(1).getId().equalsIgnoreCase("12"));
		assertTrue(stations.get(1).getName().equalsIgnoreCase("bank"));
	}

}
