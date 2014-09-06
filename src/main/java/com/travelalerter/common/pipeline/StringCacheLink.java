package com.travelalerter.common.pipeline;

import org.apache.commons.lang3.StringUtils;

/**
 * Given a String input, this will compare against a cache, and when different will pass it to the successor as a
 * String. Any initial NULLs will not be passed on.
 *
 * Created by Jim on 22/08/2014.
 */
public class StringCacheLink implements ILink<String, String> {

	private String cache;
	private ILink<String, ?> successor;

	@Override
	public void process(String... params) {
		String input = params[0];
		if (!StringUtils.equals(input, cache)) {
			cache = input;
			processNext(cache);
		}
	}

	private void processNext(String input) {
		if (successor != null) {
			successor.process(new String[] {input});
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<String, OutTypeSuccessor> connectTo(ILink<String, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
