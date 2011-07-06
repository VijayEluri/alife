package co.edu.unal.alife.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;

public class TestMapDeltaPopulation {
	
	@Test
	public void testMapDeltaPopulationIntBoolean() {
		final int N = 30;
		MapDeltaPopulation population = new MapDeltaPopulation(N, false);
		int size = population.getSize();
		assertEquals(N, size);
	}
	
	@Test
	public void testMapDeltaPopulationInt() {
		final int N = 10;
		MapDeltaPopulation population = new MapDeltaPopulation(N);
		int size = population.getSize();
		assertEquals(2 * N + 1, size);
	}
	
	@Test
	public void testMapDeltaPopulationSetOfDouble() {
		List<Double> list = Arrays.asList(new Double[] { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 });
		HashSet<Double> set = new HashSet<Double>(list);
		MapDeltaPopulation population = new MapDeltaPopulation(set);
		Set<Double> positions = population.getPositions();
		assertFalse(positions.retainAll(list));
	}
	
	@Test
	public void testSetElementDelta() {
		final int N = 10;
		final double k = 0.5;
		MapDeltaPopulation population = new MapDeltaPopulation(N);
		Set<Double> positions = population.getPositions();
		for (Double p : positions) {
			population.setElementDelta(p, p + k);
		}
		for (Double p : positions) {
			assertTrue(population.getElementDelta(p).doubleValue() == p + k);
		}
	}
	
	@Test
	public void testSetElementValue() {
		final int N = 10;
		final double k = 0.5;
		MapDeltaPopulation population = new MapDeltaPopulation(N);
		Set<Double> positions = population.getPositions();
		for (Double p : positions) {
			population.setElementValue(p, p + k);
		}
		for (Double p : positions) {
			assertTrue(population.getElementValue(p).doubleValue() == p + k);
		}
	}
	
	@Test
	public void testUpdatePopulationDelta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testHasNextPopulation() {
		final int N = 3;
		MapDeltaPopulation population = new MapDeltaPopulation(N, false);
		DeltaPopulation population2 = population.cloneEmpty(N);
		population.setNextPopulation(population2);
		assertTrue(population.hasNextPopulation());
		assertFalse(population2.hasNextPopulation());
	}
	
	@Test
	public void testToString() {
		final int N = 3;
		final String result = "0.0\t0.0\t0.0\t";
		MapDeltaPopulation population = new MapDeltaPopulation(N, false);
		assertEquals(population.toString(), result);
	}
	
	@Test
	public void testCloneEmptyInt() {
		final int N0 = 3;
		final int N1 = 5;
		final double pos0 = 0.0;
		final double val1 = 1.0;
		MapDeltaPopulation population = new MapDeltaPopulation(N0, false);
		population.setElementValue(pos0, val1);
		DeltaPopulation population2 = population.cloneEmpty(N1);
		
		assertFalse(population == population2);
		assertTrue(population2.getSize() == N1);
		
		assertTrue(population.getElementValue(pos0) == val1);
		assertFalse(population2.getElementValue(pos0) == val1);
	}
	
	@Test
	public void testCloneEmptySetOfDouble() {
		final int N0 = 3;
		final int N1 = 5;
		final double pos0 = 0.0;
		final double val1 = 1.0;

		List<Double> list = Arrays.asList(new Double[] { 0.0, 0.5, 1.0, 1.5, 2.0});
		HashSet<Double> set = new HashSet<Double>(list);

		MapDeltaPopulation population = new MapDeltaPopulation(N0, false);
		population.setElementValue(pos0, val1);
		DeltaPopulation population2 = population.cloneEmpty(set);
		
		assertFalse(population == population2);
		assertTrue(population2.getSize() == set.size());
		
		assertTrue(population.getElementValue(pos0) == val1);
		assertFalse(population2.getElementValue(pos0) == val1);
		
		Set<Double> positions = population2.getPositions();
		assertFalse(positions.retainAll(list));
	}
}
