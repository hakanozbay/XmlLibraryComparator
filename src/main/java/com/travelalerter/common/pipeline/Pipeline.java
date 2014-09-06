package com.travelalerter.common.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jim on 04/09/2014.
 */
public class Pipeline {

	public static <InputType, OutputType> ILink<InputType, OutputType> create() {
		return new Head<InputType, OutputType>();
	}

	public static class Head<InputType, OutputType> implements ILink<InputType, OutputType> {

		private final Logger LOGGER = LoggerFactory.getLogger(Head.class);
		private ILink<OutputType, ?> successor;

		@Override
		public void process(InputType... params) {
			processNext((OutputType[]) params);
		}

		private void processNext(OutputType[] param) {
			if (successor != null) {
				successor.process(param);
			}
		}

		@Override
		public <OutTypeSuccessor> ILink<OutputType, OutTypeSuccessor> connectTo(ILink<OutputType, OutTypeSuccessor> link) {
			successor = link;
			return link;
		}
	}

}
