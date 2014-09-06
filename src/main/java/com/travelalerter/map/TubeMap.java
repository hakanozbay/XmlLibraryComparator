package com.travelalerter.map;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;

import com.travelalerter.domain.Stop;

public class TubeMap {

	private final UndirectedGraph<Stop, LineEdge> graph;

	public TubeMap(UndirectedGraph<Stop, LineEdge> graph) {
		assert (graph != null) : "Graph mustn't be null";

		this.graph = graph;
	}

	/**
	 * Returns all paths between these two Stops, if any such paths exist. The two stops must be on the same line.
	 * <p/>
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public Collection<LinePath> getPaths(Stop from, Stop to) {
		assert(from.isSameLine(to)) : "From and to Stops aren't on the same line: {" + from + "} vs {" + to + "}";

		DepthTrackingBreadthFirstIterator<Stop, LineEdge> bfsGraphIterator = new DepthTrackingBreadthFirstIterator<Stop, LineEdge>(graph, from);

		if (bfsGraphIterator.hasNext()) {
			Set<LinePath> completePaths = new HashSet<LinePath>(5); // XXX Jim: why does this needs to be a set
			List<LinePath> possiblePaths = new ArrayList(10);

			do {
				possiblePaths = extendPaths(possiblePaths, graph.edgesOf(bfsGraphIterator.next()), from);

				// which ones has reached our goal?
				List<LinePath> endedPaths = filter(having(on(LinePath.class).getEndVertex(), equalTo(to)), possiblePaths);
				List<LinePath> deadPaths = filter(having(on(LinePath.class).getEdgeList().size(), lessThan(bfsGraphIterator.getCurrentDepth() - 1)), possiblePaths);

				possiblePaths.removeAll(endedPaths);
				possiblePaths.removeAll(deadPaths);
				completePaths.addAll(endedPaths);
			} while (bfsGraphIterator.hasNext() && !possiblePaths.isEmpty());

			return completePaths;
		}

		return Collections.emptySet();
	}

	/**
	 * Extends the current set of paths with the given set of edges.
	 *
	 * @param existingPaths
	 * @param edges
	 * @param from
	 * @return
	 */
	private List<LinePath> extendPaths(List<LinePath> existingPaths, Set<LineEdge> edges, final Stop from) {
		List<LinePath> extendedPaths = new ArrayList<LinePath>(edges.size() * Math.max(1, edges.size()));

		for (LineEdge edge : edges) {
			if (existingPaths.isEmpty()) {
				extendedPaths.add(new LinePath(graph, from, edge));
			} else {
				for (LinePath existingPath : existingPaths) {
					extendedPaths.add(extendPath(existingPath, edge));
				}
			}
		}

		return extendedPaths;
	}

	/**
	 * Specifically extend this path with the given edge, if it's adjacent and on the same line.
	 *
	 * @param existingPath
	 * @param edge
	 * @return
	 */
	private LinePath extendPath(LinePath existingPath, LineEdge edge) {
		if (!isConnectedUndirected(existingPath, edge)) {
			return existingPath;
		}
		
		if (!edge.isSameLine(existingPath.getEndVertex())) {
			return existingPath;
		}

		return existingPath.appendEdge(edge);
	}

	private boolean isConnectedUndirected(LinePath existingPath, LineEdge edge) {
		return graph.containsEdge(existingPath.getEndVertex(), edge.getSource()) || graph.containsEdge(existingPath.getEndVertex(), edge.getTarget());
	}
}
