package com.travelalerter.data.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelalerter.common.pipeline.ILink;
import com.travelalerter.tfl.linestatuses.LineStatuses;
import com.travelalerter.tfl.parsers.LineStatusParser;

/**
 * Given an XML of the station status, will try to parse it into the LineStatuses object. The successor will be provided
 * this object if parsing was successful.
 *
 * Created by Jim on 22/08/2014.
 */
public class LineStatusesParserLink implements ILink<String, LineStatuses> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LineStatusesParserLink.class);
	private ILink<LineStatuses, ?> successor;

	@Override
	public void process(String... params) {
		String xml = params[0];
		try {
			processNext(LineStatusParser.parse(xml));
		} catch (Exception e) {
			LOGGER.error("Problem parsing line status xml " + xml, e);
		}
	}

	private void processNext(LineStatuses statuses) {
		if (successor != null) {
			successor.process(new LineStatuses[] {statuses});
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<LineStatuses, OutTypeSuccessor> connectTo(ILink<LineStatuses, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
