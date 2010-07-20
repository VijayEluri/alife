package co.edu.unal.alife.evolution.impl.operator;

import java.util.List;

import co.edu.unal.alife.evolution.param.S1ControllerParameters;
import jml.evolution.Environment;
import jml.evolution.binary.operators.SingleBitMutation;
import jml.random.UniformNumberGenerator;

public class S1OutputTTypeMutation extends SingleBitMutation {

	public S1OutputTTypeMutation(Environment environment) {
		super(environment);
	}

	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
		List<Boolean> boolTs = genome.getOutParams().getBoolTs();
		int size = boolTs.size();
		int pos = -1;
		try {
			UniformNumberGenerator g = new UniformNumberGenerator(size);
			pos = g.newInt();
			Boolean b = boolTs.get(pos);
			boolTs.set(pos, !b);
		} catch (Exception e) {
			System.err.println("[Mutation]" + e.getMessage());
		}
		return new Integer(pos);
	}

}
