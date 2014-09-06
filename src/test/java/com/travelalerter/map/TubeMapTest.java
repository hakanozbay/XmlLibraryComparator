package com.travelalerter.map;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import com.travelalerter.domain.Stop;
import com.travelalerter.domain.lines.Line;
import com.travelalerter.domain.stations.Station;

public class TubeMapTest {
	
	@SuppressWarnings("serial")
	@Test
	public void testSimpleKenningtonToStockwell() {
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

		// the path we expect between kennington and stockwell
		final LinePath kennington2StockwellPath = new LinePath(graph, kenningtonStop, stockwellStop, 0d,
			new ArrayList<LineEdge>() {
				{
					add(stockwell2Oval);
					add(oval2Kennington);
				}
			}, new HashSet<Stop>() {
				{
					add(kenningtonStop);
					add(ovalStop);
					add(stockwellStop);
				}
			}
		);

		Collection<LinePath> expectedLinePaths = new HashSet<LinePath>() {
			{
				add(kennington2StockwellPath);
			}
		};

		// build and test the map
		TubeMap map = new TubeMap(graph);

		// retrieve and check paths
		Collection<LinePath> resultPaths = map.getPaths(kenningtonStop, stockwellStop);
		assertEquals(expectedLinePaths, resultPaths);
	}

	@Test
	public void testDualRoutesKenningtonToStockwell() {
		Line northernLine = new Line("1", "Northern");

		// setup
		Station kenningtonStation = new Station("1", "Kennington");
		Station stockwellStation = new Station("2", "Stockwell");
		Station ovalStation = new Station("3", "Oval");
		Station waterlooStation = new Station("4", "Waterloo");
		Station elephantAndCastleStation = new Station("5", "Elephant & Castle");

		final Stop kenningtonStop = Stop.retrieve(kenningtonStation, northernLine);
		final Stop stockwellStop = Stop.retrieve(stockwellStation, northernLine);
		final Stop ovalStop = Stop.retrieve(ovalStation, northernLine);
		final Stop waterlooStop = Stop.retrieve(waterlooStation, northernLine);
		final Stop elephantAndCastleStop = Stop.retrieve(elephantAndCastleStation, northernLine);

		UndirectedGraph<Stop, LineEdge> graph = new SimpleGraph<Stop, LineEdge>(LineEdge.class);
		graph.addVertex(kenningtonStop);
		graph.addVertex(ovalStop);
		graph.addVertex(stockwellStop);
		graph.addVertex(waterlooStop);
		graph.addVertex(elephantAndCastleStop);

		final LineEdge stockwell2Oval = new LineEdge(northernLine);
		final LineEdge oval2Kennington = new LineEdge(northernLine);
		final LineEdge stockwell2Waterloo = new LineEdge(northernLine);
		final LineEdge waterloo2ElephantAndCastle = new LineEdge(northernLine);
		final LineEdge elephantAndCastle2Oval = new LineEdge(northernLine);
		graph.addEdge(stockwellStop, ovalStop, stockwell2Oval);
		graph.addEdge(ovalStop, kenningtonStop, oval2Kennington);
		graph.addEdge(stockwellStop, waterlooStop, stockwell2Waterloo);
		graph.addEdge(waterlooStop, elephantAndCastleStop, waterloo2ElephantAndCastle);
		graph.addEdge(elephantAndCastleStop, ovalStop, elephantAndCastle2Oval);

		// the path we expect between kennington and stockwell
		final LinePath kennington2StockwellPath1 = new LinePath(graph, kenningtonStop, stockwellStop, 0d,
			new ArrayList<LineEdge>() {
				{
					add(stockwell2Oval);
					add(oval2Kennington);
				}
			}, new HashSet<Stop>() {
				{
					add(kenningtonStop);
					add(ovalStop);
					add(stockwellStop);
				}
			}
		);

		final LinePath kennington2StockwellPath2 = new LinePath(graph, kenningtonStop, stockwellStop, 0d,
			new ArrayList<LineEdge>() {
				{
					add(stockwell2Waterloo);
					add(waterloo2ElephantAndCastle);
					add(elephantAndCastle2Oval);
					add(oval2Kennington);
				}
			}, new HashSet<Stop>() {
				{
					add(kenningtonStop);
					add(ovalStop);
					add(stockwellStop);
					add(waterlooStop);
					add(elephantAndCastleStop);
				}
			}
		);

		Collection<LinePath> expectedLinePaths = new HashSet<LinePath>() {
			{
				add(kennington2StockwellPath1);
				add(kennington2StockwellPath2);
			}
		};

		// build and test the map
		TubeMap map = new TubeMap(graph);

		// retrieve and check paths
		Collection<LinePath> resultPaths = map.getPaths(kenningtonStop, stockwellStop);
		assertEquals(expectedLinePaths, resultPaths);
	}

}
