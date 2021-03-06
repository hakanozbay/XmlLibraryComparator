package com.travelalerter.tfl.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.travelalerter.tfl.linestatuses.BranchDisruption;
import com.travelalerter.tfl.linestatuses.BranchDisruptions;
import com.travelalerter.tfl.linestatuses.Line;
import com.travelalerter.tfl.linestatuses.LineStatus;
import com.travelalerter.tfl.linestatuses.LineStatuses;
import com.travelalerter.tfl.linestatuses.StationFrom;
import com.travelalerter.tfl.linestatuses.StationTo;
import com.travelalerter.tfl.linestatuses.Status;
import com.travelalerter.tfl.linestatuses.StatusType;

public class LineStatusParserTests {

	File _source;

	@Before
	public void setUp() throws Exception {
		_source = new File(ClassLoader.getSystemResource("tfl/LineStatuses.xml")
				.toURI());
	}

	@Test
	public void GetListOfLineStatusesIsNotEmptyTest() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		assertNotNull(lineStatuses);
		assertFalse(lineStatuses.getLineStatuses().isEmpty());
	}

	@Test
	public void LineStatusZeroExists() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");

		assertNotNull(lineStatusZero);
	}

	@Test
	public void LineStatusZeroHasCorrectLine() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");

		Line lineStatusZeroLine = lineStatusZero.getLine();

		assertNotNull(lineStatusZero);

		assertEquals("1", lineStatusZeroLine.getId());
		assertEquals("Bakerloo", lineStatusZeroLine.getName());
	}

	@Test
	public void LineStatusZeroHasNoBranchDisruptions() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");
		BranchDisruptions branchDisruptions = lineStatusZero.getBranchDisruptions();
		
		assertNull(branchDisruptions.getBranchDisruptions());
	}
	
	@Test
	public void LineStatusZeroHasCorrectStatus() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");
		Status status = lineStatusZero.getStatus();
		
		assertNotNull(status);
		assertEquals("GS", status.getID());
		assertEquals("Good Service", status.getDescription());
		assertEquals("GoodService", status.getCssClass());		
		assertEquals("true", status.getIsActive());
	}
	
	@Test
	public void LineStatusZeroHasCorrectStatusTypes() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");
		StatusType statusType = lineStatusZero.getStatus().getStatusType();
		
		assertEquals("1", statusType.getID());
		assertEquals("Line", statusType.getDescription());
	}
	
	@Test
	public void LineStatusTwoHasOneBranchDisruption() throws Exception{
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusTwo = getLineStatus(lineStatuses, "2");
		
		BranchDisruptions branchDisruptions = lineStatusTwo.getBranchDisruptions();
		
		assertNotNull(branchDisruptions.getBranchDisruptions());
		assertEquals(1, branchDisruptions.getBranchDisruptions().size());
	}
	
	@Test
	public void LineStatusTwoHasCorrectBranchDisruptionsStations() throws Exception{
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusTwo = getLineStatus(lineStatuses, "2");
		
		BranchDisruptions branchDisruptions = lineStatusTwo.getBranchDisruptions();
		BranchDisruption branchDisruption = branchDisruptions.getBranchDisruptions().get(0);
		StationFrom stationFrom = branchDisruption.getStationFrom();
		StationTo stationTo = branchDisruption.getStationTo();
		assertEquals("Richmond", stationTo.getName());
		assertEquals("190", stationTo.getID());
		assertEquals("Turnham Green", stationFrom.getName());
		assertEquals("238", stationFrom.getID());
	}
	
	@Test
	public void LineStatusTwoHasCorrectBranchDisruptionsStatus() throws Exception{
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusTwo = getLineStatus(lineStatuses, "2");
		
		BranchDisruptions branchDisruptions = lineStatusTwo.getBranchDisruptions();
		BranchDisruption branchDisruption = branchDisruptions.getBranchDisruptions().get(0);
		Status status = branchDisruption.getStatus();
		assertNotNull(status);
		assertEquals("PS", status.getID());	
		assertEquals("DisruptedService", status.getCssClass());		
		assertEquals("Part Suspended", status.getDescription());
		assertEquals("true", status.getIsActive());
	}
	
	@Test
	public void LineStatusTwoHasCorrectBranchDisruptionsStatusTypes() throws Exception {
		LineStatuses lineStatuses = LineStatusParser.parse(_source);
		LineStatus lineStatusTwo = getLineStatus(lineStatuses, "2");
		
		BranchDisruptions branchDisruptions = lineStatusTwo.getBranchDisruptions();
		BranchDisruption branchDisruption = branchDisruptions.getBranchDisruptions().get(0);
		Status status = branchDisruption.getStatus();
		
		StatusType statusType = status.getStatusType();
		
		assertEquals("1", statusType.getID());
		assertEquals("Line", statusType.getDescription());
	}
	
	private LineStatus getLineStatus(LineStatuses lineStatuses, String id) {
		List<LineStatus> lineStatusList = lineStatuses.getLineStatuses();

		for (LineStatus lineStatus : lineStatusList) {
			if (lineStatus.getId().equals(id))
				return lineStatus;
		}

		return null;
	}
	
}
