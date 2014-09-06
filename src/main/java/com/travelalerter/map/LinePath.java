package com.travelalerter.map;

import static ch.lambdaj.Lambda.joinFrom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.jcip.annotations.Immutable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;

import com.travelalerter.domain.Stop;

/**
 * Created by Jim on 25/08/2014.
 */
@Immutable
public class LinePath implements GraphPath<Stop, LineEdge> {

	private final Graph<Stop, LineEdge> graph;
	private final List<LineEdge> edgeList;
	private final Set<Stop> vertexSet;
	private final Stop startVertex;
	private final Stop endVertex;
	private final double weight;

	public LinePath(Graph<Stop, LineEdge> graph, final Stop startVertex) {
		this(graph, startVertex, startVertex, 0d, (List<LineEdge>) (List<?>) Collections.emptyList(), Collections.singleton(startVertex));
	}

	public LinePath(final Graph<Stop, LineEdge> graph, final Stop startVertex, final LineEdge edge) {
		this(graph, startVertex, Graphs.getOppositeVertex(graph, edge, startVertex), 0d, Collections.singletonList(edge), new HashSet<Stop>() {{
			add(startVertex);
			add(Graphs.getOppositeVertex(graph, edge, startVertex));
		}});
	}

	public LinePath(Graph<Stop, LineEdge> graph, final Stop startVertex, final Stop endVertex, final double weight, final List<LineEdge> edgeList, Set<Stop> vertexSet) {
		assert(graph != null) : "Graph cannot be null";
		assert(!Double.isNaN(weight)) : "Weight cannot be NaN";
		assert(startVertex != null) : "Start vertex mustn't be null";
		assert(endVertex != null) : "End vertex mustn't be null";
		assert(edgeList != null) : "Edge list mustn't be null";
		assert(vertexSet != null) : "Vertex set mustn't be null";

		this.graph = graph;
		this.edgeList = Collections.unmodifiableList(edgeList);
		this.vertexSet = Collections.unmodifiableSet(vertexSet);
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}

	public int getVertexCount() {
		return vertexSet.size();
	}

	public boolean containsEdge(LineEdge edge) {
		return edge != null && edgeList.contains(edge);
	}

	public LinePath appendEdge(final LineEdge edge) {
		assert(edge != null) : "Cannot append null edge";

		boolean equalsSource = endVertex.equals(edge.getSource());
		boolean equalsTarget = endVertex.equals(edge.getTarget());

		if (equalsSource || equalsTarget) {
			if (!containsEdge(edge)) {
				final Stop newEndVertex = equalsSource ? edge.getTarget() : edge.getSource();
				return new LinePath(graph, startVertex, newEndVertex, weight, new ArrayList<LineEdge>() {
					{
						add(edge);
						addAll(edgeList);
					}
				}, new HashSet<Stop>() {
					{
						add(newEndVertex);
						addAll(vertexSet);
					}
				});
			}
		}

		return this;
	}

	@Override
	public Graph<Stop, LineEdge> getGraph() {
		return graph;
	}

	@Override
	public Stop getStartVertex() {
		return startVertex;
	}

	@Override
	public Stop getEndVertex() {
		return endVertex;
	}

	@Override
	public List<LineEdge> getEdgeList() {
		return edgeList;
	}

	public Set<Stop> getVertexSet() {
		return vertexSet;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(LinePath.class.getSimpleName());
		sb.append("[");
		sb.append(joinFrom(edgeList, " -> "));
		sb.append("]");

		return sb.toString();
//		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
