package com.travelalerter.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import com.travelalerter.domain.Stop;
import com.travelalerter.domain.lines.Line;
import com.travelalerter.domain.stations.Station;

/**
 * Created by Jim on 27/08/2014.
 */
public class LinePathTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testBasicConstructor() {
		// setup
		Graph<Stop, LineEdge> graph = mock(Graph.class);
		Stop startVertex = mock(Stop.class);

		LinePath path = new LinePath(graph, startVertex);

		// check retrieval
		assertEquals(0d, path.getWeight(), 0d);
		assertEquals(graph, path.getGraph());
		assertEquals(startVertex, path.getStartVertex());
		assertEquals(startVertex, path.getEndVertex());
	}

	@SuppressWarnings({ "unchecked", "serial" })
	@Test
	public void testFullConstructor() {
		// setup
		Graph<Stop, LineEdge> graph = mock(Graph.class);
		final Stop startVertex = mock(Stop.class);
		final Stop endVertex = mock(Stop.class);
		double weight = 128d;
		List<LineEdge> edges = new ArrayList<LineEdge>() {
			{
				add(mock(LineEdge.class));
			}
		};
		Set<Stop> stops = new HashSet<Stop>() {
			{
				add(startVertex);
				add(endVertex);
			}
		};

		LinePath path = new LinePath(graph, startVertex, endVertex, weight, edges, stops);

		// check retrieval
		assertEquals(weight, path.getWeight(), 0d);
		assertEquals(graph, path.getGraph());
		assertEquals(startVertex, path.getStartVertex());
		assertEquals(endVertex, path.getEndVertex());
		assertEquals(edges, path.getEdgeList());
		assertEquals(stops, path.getVertexSet());
	}

	@SuppressWarnings("serial")
	@Test
	public void testAppendEdgeConnected() {
		// setup
		Line northernLine = new Line("1", "Northern");

		Station kenningtonStation = new Station("1", "Kennington");
		Station stockwellStation = new Station("2", "Stockwell");
		Station ovalStation = new Station("3", "Oval");

		final Stop kenningtonStop = Stop.retrieve(kenningtonStation, northernLine);
		final Stop stockwellStop = Stop.retrieve(stockwellStation, northernLine);
		final Stop ovalStop = Stop.retrieve(ovalStation, northernLine);

		UndirectedGraph<Stop, LineEdge> graph = new SimpleGraph<Stop, LineEdge>(LineEdge.class);
		graph.addVertex(kenningtonStop);
		graph.addVertex(ovalStop);
		graph.addVertex(stockwellStop);

		final LineEdge stockwell2Oval = new LineEdge(northernLine);
		final LineEdge oval2Kennington = new LineEdge(northernLine);
		graph.addEdge(stockwellStop, ovalStop, stockwell2Oval);
		graph.addEdge(ovalStop, kenningtonStop, oval2Kennington);

		List<LineEdge> edgesBefore = new ArrayList<LineEdge>() {
			{
				add(oval2Kennington);
			}
		};
		Set<Stop> stopsBefore = new HashSet<Stop>() {
			{
				add(kenningtonStop);
				add(ovalStop);
			}
		};

		// create a path and append a valid edge to it
		LinePath pathBefore = new LinePath(graph, kenningtonStop, ovalStop, 0d, edgesBefore, stopsBefore);
		LinePath pathAfter = pathBefore.appendEdge(stockwell2Oval);

		assertNotEquals(pathBefore, pathAfter);

		List<LineEdge> edgesAfter = new ArrayList<LineEdge>() {
			{
				add(stockwell2Oval);
				add(oval2Kennington);
			}
		};
		Set<Stop> stopsAfter = new HashSet<Stop>() {
			{
				add(kenningtonStop);
				add(ovalStop);
				add(stockwellStop);
			}
		};

		assertEquals(edgesAfter, pathAfter.getEdgeList());
		assertEquals(stopsAfter, pathAfter.getVertexSet());

		// try to append same path, which would not affect anything
		LinePath pathAfterAgain = pathAfter.appendEdge(stockwell2Oval);

		assertEquals(pathAfter, pathAfterAgain);
	}

	@SuppressWarnings("serial")
	@Test
	public void testAppendEdgeDisconnected() {
		// setup
		Line northernLine = new Line("1", "Northern");

		Station kenningtonStation = new Station("1", "Kennington");
		Station stockwellStation = new Station("2", "Stockwell");
		Station ovalStation = new Station("3", "Oval");

		final Stop kenningtonStop = Stop.retrieve(kenningtonStation, northernLine);
		final Stop stockwellStop = Stop.retrieve(stockwellStation, northernLine);
		final Stop ovalStop = Stop.retrieve(ovalStation, northernLine);

		UndirectedGraph<Stop, LineEdge> graph = new SimpleGraph<Stop, LineEdge>(LineEdge.class);
		graph.addVertex(kenningtonStop);
		graph.addVertex(ovalStop);
		graph.addVertex(stockwellStop);

		final LineEdge stockwell2Oval = new LineEdge(northernLine);
		graph.addEdge(stockwellStop, ovalStop, stockwell2Oval);

		List<LineEdge> edgesBefore = Collections.emptyList();
		Set<Stop> stopsBefore = new HashSet<Stop>() {
			{
				add(kenningtonStop);
			}
		};

		// create a path and append an unconnected edge to it
		LinePath pathBefore = new LinePath(graph, kenningtonStop, kenningtonStop, 0d, edgesBefore, stopsBefore);
		LinePath pathBeforeAgain = pathBefore.appendEdge(stockwell2Oval);

		assertEquals(pathBefore, pathBeforeAgain);
	}

}

