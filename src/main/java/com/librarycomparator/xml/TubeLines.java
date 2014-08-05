package com.librarycomparator.xml;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class TubeLines {
	public List<TubeLine> getTubeLines() {
		return lines;
	}

	@ElementList
	public List<TubeLine> lines ;
}
