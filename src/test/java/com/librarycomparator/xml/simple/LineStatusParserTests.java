package com.librarycomparator.xml.simple;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.librarycomparator.xml.LineStatusParser;
import com.librarycomparator.xml.simple.LineStatuses.BranchDisruptions;
import com.librarycomparator.xml.simple.LineStatuses.Line;
import com.librarycomparator.xml.simple.LineStatuses.LineStatus;
import com.librarycomparator.xml.simple.LineStatuses.LineStatuses;
import com.librarycomparator.xml.simple.LineStatuses.Status;
import com.librarycomparator.xml.simple.LineStatuses.StatusType;

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
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
		assertNotNull(lineStatuses);
		assertFalse(lineStatuses.getLineStatuses().isEmpty());
	}

	@Test
	public void LineStatusZeroExists() throws Exception {
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");

		assertNotNull(lineStatusZero);
	}

	@Test
	public void LineStatusZeroHasCorrectLine() throws Exception {
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");

		Line lineStatusZeroLine = lineStatusZero.getLine();

		assertNotNull(lineStatusZero);

		assertEquals("1", lineStatusZeroLine.getId());
		assertEquals("Bakerloo", lineStatusZeroLine.getName());
	}

	@Test
	public void LineStatusZeroHasNoBranchDisruptions() throws Exception {
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");
		BranchDisruptions branchDisruptions = lineStatusZero.getBranchDisruptions();
		
		assertNull(branchDisruptions.getBranchDisruptions());
	}
	
	@Test
	public void LineStatusZeroHasCorrectStatus() throws Exception {
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
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
		LineStatuses lineStatuses = _lineStatusParser.Parse(_source);
		LineStatus lineStatusZero = getLineStatus(lineStatuses, "0");
		List<StatusType> statusTypes = lineStatusZero.getStatus().getStatusTypes();
		
		assertEquals(1, statusTypes.size());
		StatusType statusType = statusTypes.get(0);
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
