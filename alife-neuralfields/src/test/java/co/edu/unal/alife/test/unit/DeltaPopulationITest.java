package co.edu.unal.alife.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;

public abstract class DeltaPopulationITest {
	
	protected static final int N = 5;
	protected DeltaPopulation pop;
	
	@Test
	public void testSetElementDelta() {
		final double k = 0.5;
		Set<Double> positions = pop.getPositions();
		for (Double p : positions) {
			pop.setElementDelta(p, p + k);
		}
		for (Double p : positions) {
			assertTrue(pop.getElementDelta(p) == p + k);
		}
	}
	
	@Test
	public void testSetElementValue() {
		final double k = 0.5;
		Set<Double> positions = pop.getPositions();
		for (Double p : positions) {
			pop.setElementValue(p, p + k);
		}
		for (Double p : positions) {
			assertTrue(pop.getElementValue(p) == p + k);
		}
	}
	
//	@Test
//	public void testUpdatePopulationDelta() {
//	}
	
	@Test
	public void testHasNextPopulation() {
		DeltaPopulation population2 = pop.cloneEmpty(N);
		pop.setNextPopulation(population2);
		assertTrue(pop.hasNextPopulation());
		assertFalse(population2.hasNextPopulation());
	}
	
	@Test
	public void testToString() {
		final String result = "0.0\t0.0\t0.0\t0.0\t0.0\t";
		assertEquals(pop.toString(), result);
	}
	
	@Test
	public void testCloneEmptyInt() {
		final int N1 = 3;
		final double pos0 = 0.0;
		final double val1 = 1.0;
		pop.setElementValue(pos0, val1);
		DeltaPopulation population2 = pop.cloneEmpty(N1);
		
		assertFalse(pop == population2);
		assertTrue(population2.getSize() == N1);
		
		assertTrue(pop.getElementValue(pos0) == val1);
		assertFalse(population2.getElementValue(pos0) == val1);
	}
	
//	@Test
//	public void testCloneEmptySetOfDouble() {
//		final double pos0 = 0.0;
//		final double val1 = 1.0;
//		
//		List<Double> list = Arrays.asList(new Double[] { 0.0, 0.5, 1.0 });
//		HashSet<Double> set = new HashSet<Double>(list);
//		
//		pop.setElementValue(pos0, val1);
//		DeltaPopulation population2 = pop.cloneEmpty(set);
//		
//		assertFalse(pop == population2);
//		assertTrue(population2.getSize() == set.size());
//		
//		assertTrue(pop.getElementValue(pos0) == val1);
//		assertFalse(population2.getElementValue(pos0) == val1);
//		
//		Set<Double> positions = population2.getPositions();
//		assertFalse(positions.retainAll(list));
//	}
	
}
