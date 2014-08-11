package com.librarycomparator.xml.service;

import com.librarycomparator.xml.simple.linestatuses.LineStatuses;
import com.librarycomparator.xml.simple.tubelines.TubeLines;

public interface ITubeDataParser {
	LineStatuses ParseLineStatuses(String xml);
	TubeLines ParseTubeLines(String xml);
}
