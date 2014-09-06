package com.travelalerter.common.pipeline;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given an Object, this will log out to DEBUG it's String representation. The successor will be passed this Object (and
 * it'll be cast to it's input. Given the level is DEBUG, this can be useful for type conversions.
 *
 * Created by Jim on 22/08/2014.
 */
public class LoggerLink<OutType> implements ILink<Object, OutType> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerLink.class);
	private ILink<OutType, ?> successor;

	@Override
	public void process(Object... params) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{}", Arrays.toString(params));
		}

		processNext((OutType[]) params);
	}

	private void processNext(OutType[] param) {
		if (successor != null) {
			successor.process(param);
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<OutType, OutTypeSuccessor> connectTo(ILink<OutType, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
