package com.librarycomparator.xml.simple.TubeLines;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "lines")
public class TubeLines {

	@ElementList(entry = "line", inline = true)
	private List<TubeLine> lines;

	public List<TubeLine> getTubeLines() {
		return lines;
	}
}
