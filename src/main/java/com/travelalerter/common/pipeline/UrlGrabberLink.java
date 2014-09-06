package com.travelalerter.common.pipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a Url address as a String, this will attempt to read it's contents. The successor will be passed it's contents
 * if the read was successful and non-empty.
 *
 * Created by Jim on 04/09/2014.
 */
public class UrlGrabberLink implements ILink<String, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlGrabberLink.class);
	private static final String NEWLINE = System.getProperty("line.separator");
	private ILink<String, ?> successor;

	@Override
	public void process(String... params) {
		String url = params[0];
		try {
			InputStream inputStream = getURLStream(new URL(url));
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String contents = getStringFromReader(reader);

			if (!StringUtils.isBlank(contents)) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Read from url {} contents {}", url, contents);
				}

				processNext(contents);
			}
		} catch (Exception e) {
			LOGGER.error("Problem pulling down url " + url, e);
		}
	}

	private String getStringFromReader(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		String currentLine;

		try {
			while ((currentLine = reader.readLine()) != null) {
				builder.append(currentLine);
				builder.append(NEWLINE);
			}
		} finally {
			reader.close();
		}

		return builder.toString();
	}

	private InputStream getURLStream(URL url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.connect();
		return conn.getInputStream();
	}

	private void processNext(String contents) {
		if (successor != null) {
			successor.process(new String[] {contents});
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<String, OutTypeSuccessor> connectTo(ILink<String, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
