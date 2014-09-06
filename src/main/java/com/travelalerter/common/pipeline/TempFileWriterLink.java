package com.travelalerter.common.pipeline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a String of contents, this will write to a new file name under the temp directory. If write is successful then
 * then successor will be passed the file name.
 *
 * Created by Jim on 04/09/2014.
 */
public class TempFileWriterLink implements ILink<String, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TempFileWriterLink.class);
	private ILink<String, ?> successor;

	@Override
	public void process(String... params) {
		String contents = params[0];
		try {
			File file = getTempFile(contents);
			writeFileContents(file, contents);

			String fileName = file.getAbsolutePath();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Wrote to {} contents {}", fileName, contents);
			}

			processNext(fileName);
		} catch (Exception e) {
			LOGGER.error("Problem writing temp file", e);
		}
	}

	private void writeFileContents(File file, String contents) throws IOException {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(contents);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					LOGGER.error("Problem closing file", e);
				}
			}
		}
	}

	private File getTempFile(String param) throws IOException {
		return File.createTempFile(Integer.toHexString(System.identityHashCode(param)), ".tmp");
	}

	private void processNext(String fileName) {
		if (successor != null) {
			successor.process(new String[] {fileName});
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<String, OutTypeSuccessor> connectTo(ILink<String, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
