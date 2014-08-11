package com.librarycomparator.xml.simple.linestatuses;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(name="ArrayOfLineStatus")
public class LineStatuses {

	@ElementList(entry = "LineStatus", inline = true)
	private List<LineStatus> _lineStatuses;

	public List<LineStatus> getLineStatuses() {
		return _lineStatuses;
	}
	
}
