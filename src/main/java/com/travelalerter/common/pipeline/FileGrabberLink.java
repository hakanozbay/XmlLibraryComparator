package com.travelalerter.common.pipeline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a file name, will try to open and read the entire contents of that file. The successor will be provided a
 * non-empty String of the contents upon a successful read.
 *
 * Created by Jim on 22/08/2014.
 */
public class FileGrabberLink implements ILink<String, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileGrabberLink.class);
	private static final String NEWLINE = System.getProperty("line.separator");
	private ILink<String, ?> successor;

	@Override
	public void process(String... params) {
		String fileName = params[0];

		try {
			BufferedReader reader = getBufferedReader(fileName);
			String contents = getStringFromReader(reader);

			if (StringUtils.isNotBlank(contents)) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Read from file {} contents {}", fileName, contents);
				}

				processNext(contents);
			}
		} catch (Exception e) {
			LOGGER.error("Problem grabbing file " + fileName, e);
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

	private BufferedReader getBufferedReader(String param) throws FileNotFoundException {
		InputStream in = getClass().getResourceAsStream(param);
		BufferedReader reader;
		if (in != null) {
			reader = new BufferedReader(new InputStreamReader(in));
		} else {
			FileReader fileReader = new FileReader(param);
			reader = new BufferedReader(fileReader);
		}
		return reader;
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
