package com.travelalerter.common.pipeline;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a filename, will try to delete that file. The successor will be provided the File object.
 *
 * Created by Jim on 04/09/2014.
 */
public class FileDeleterLink implements ILink<String, File> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDeleterLink.class);
	private ILink<File, ?> successor;

	@Override
	public void process(String... params) {
		String fileName = params[0];
		File file = new File(fileName);
		if (!file.delete()) {
			LOGGER.warn("Failed to delete {}", fileName);
		}

		processNext(file);
	}

	private void processNext(File param) {
		if (successor != null) {
			successor.process(new File[] {param});
		}
	}

	@Override
	public <OutTypeSuccessor> ILink<File, OutTypeSuccessor> connectTo(ILink<File, OutTypeSuccessor> link) {
		successor = link;
		return link;
	}
}
