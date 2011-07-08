package co.edu.unal.alife.test.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.impl.ArrayDeltaPopulation;

public class ArrayDeltaPopulationTest extends DeltaPopulationITest {
	
	@Before
	public void setUp() throws Exception {
		pop = new ArrayDeltaPopulation(N,false);
	}
	
	@Test
	public void testMapDeltaPopulationIntBoolean() {
		final int N = 30;
		DeltaPopulation population = new ArrayDeltaPopulation(N, false);
		int size = population.getSize();
		assertEquals(N, size);
	}
	
	@Test
	public void testMapDeltaPopulationIntBoolean2() {
		final int N = 10;
		DeltaPopulation population = new ArrayDeltaPopulation(N, true);
		int size = population.getSize();
		assertEquals(2 * N + 1, size);
	}
}
