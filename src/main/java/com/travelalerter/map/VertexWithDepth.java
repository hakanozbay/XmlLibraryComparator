package com.travelalerter.map;

import net.jcip.annotations.Immutable;

/**
* Created by Jim on 27/08/2014.
*/
@Immutable
public class VertexWithDepth<V> {

	private final V vertex;
	private final int depth;

	public VertexWithDepth(V vertex, int depth) {
		this.vertex = vertex;
		this.depth = depth;
	}

	public V getVertex() {
		return vertex;
	}

	public int getDepth() {
		return depth;
	}

}
