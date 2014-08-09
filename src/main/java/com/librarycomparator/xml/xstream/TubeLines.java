package com.librarycomparator.xml.xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("lines")
public class TubeLines {

	@XStreamImplicit(itemFieldName="line")
	private List<TubeLine> lines;

	public List<TubeLine> getTubeLines() {
		return lines;
	}
}
