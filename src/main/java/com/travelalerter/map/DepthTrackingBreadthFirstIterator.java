package com.travelalerter.map;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.traverse.CrossComponentIterator;

;

/**
 * A cloned implementation of the BreadthFirstIterator but additionally tracks the depth.
 * @see DepthTrackingBreadthFirstIterator#getCurrentDepth()
 *
 * Created by Jim on 27/08/2014.
 */
public class DepthTrackingBreadthFirstIterator<V, E> extends CrossComponentIterator<V, E, Object> {

	private final Deque<VertexWithDepth<V>> queue = new ArrayDeque<VertexWithDepth<V>>();

	private int encounteredDepth = 0;
	private int currentDepth = 0;
	private WeakReference<V> parentVertex = new WeakReference<V>(null);

	/**
	 * Creates a new breadth-first iterator for the specified graph.
	 *
	 * @param g the graph to be iterated.
	 */
	public DepthTrackingBreadthFirstIterator(Graph<V, E> g)
	{
		this(g, null);
	}

	/**
	 * Creates a new breadth-first iterator for the specified graph. Iteration
	 * will start at the specified start vertex and will be limited to the
	 * connected component that includes that vertex. If the specified start
	 * vertex is <code>null</code>, iteration will start at an arbitrary vertex
	 * and will not be limited, that is, will be able to traverse all the graph.
	 *
	 * @param g the graph to be iterated.
	 * @param startVertex the vertex iteration to be started.
	 */
	public DepthTrackingBreadthFirstIterator(Graph<V, E> g, V startVertex)
	{
		super(g, startVertex);
	}

	/**
	 * Provides the depth in the tree for the currently obtained vertex. Defaults to 0 as well if nothing has been
	 * traversed yet.
	 *
	 * @return
	 */
	public int getCurrentDepth() {
		return currentDepth;
	}

	/**
	 * @see CrossComponentIterator#isConnectedComponentExhausted()
	 */
	protected boolean isConnectedComponentExhausted()
	{
		return queue.isEmpty();
	}

	/**
	 * @see CrossComponentIterator#encounterVertex(Object, Object)
	 */
	protected void encounterVertex(V vertex, E edge)
	{
		updateEncounteredDepth(vertex, edge);
		putSeenData(vertex, null);
		queue.add(new VertexWithDepth(vertex, encounteredDepth));
	}

	private void updateEncounteredDepth(V vertex, E edge) {
		// null edge means start vertex, for which we're already at depth zero
		if (edge == null) return;

		V sourceVertex = Graphs.getOppositeVertex(getGraph(), edge, vertex);
		if (sourceVertex != parentVertex.get()) {
			parentVertex = new WeakReference<V>(sourceVertex);
			encounteredDepth++;
		}
	}

	/**
	 * @see CrossComponentIterator#encounterVertexAgain(Object, Object)
	 */
	protected void encounterVertexAgain(V vertex, E edge)
	{
	}

	/**
	 * @see CrossComponentIterator#provideNextVertex()
	 */
	protected V provideNextVertex()
	{
		VertexWithDepth<V> vwd = queue.removeFirst();
		currentDepth = vwd.getDepth();
		return vwd.getVertex();
	}

}
