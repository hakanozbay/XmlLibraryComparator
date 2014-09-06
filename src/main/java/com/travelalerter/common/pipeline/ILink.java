package com.travelalerter.common.pipeline;

/**
 * Created by Jim on 22/08/2014.
 */
public interface ILink<InType, OutType> {

    public void process(InType... param);

    public <OutTypeSuccessor> ILink<OutType, OutTypeSuccessor> connectTo(ILink<OutType, OutTypeSuccessor> link);

}
