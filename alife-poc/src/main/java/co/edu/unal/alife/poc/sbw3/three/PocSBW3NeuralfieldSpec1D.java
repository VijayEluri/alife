package co.edu.unal.alife.poc.sbw3.three;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.PocNeuralfieldsHomogeneousEquation1D;

public class PocSBW3NeuralfieldSpec1D extends APocNeuralfieldSpec {

	private static final double START_POSITION = 0;
	private static final double END_POSITION = 1;

	private double h;
	private double[] positions;

	public PocSBW3NeuralfieldSpec1D(int startIndex, int length) {
		super(startIndex, length);
		this.endIndex = getStartIndex() + getLength() - 1;
		this.positions = new double[length];
		this.h = (END_POSITION - START_POSITION) / getLength();
		this.inputEquation = new PocSBW3NeuralfieldsInputEquation1D(this);
		this.outputEquation = new PocSBW3NeuralfieldsOutputEquation1D(this);
		this.homogeneousEquation = new PocNeuralfieldsHomogeneousEquation1D(
				this);
		initPositions();
	}

	protected void initPositions() {
		// There are as many bins as there are positions, all of equal size.
		// And the position of each bin is its center.
		double currentPosition = START_POSITION + h / 2;
		positions[0] = currentPosition;
		for (int i = 1; i < positions.length; i++) {
			currentPosition += h;
			positions[i] = currentPosition;
		}
	}

	@Override
	public double getPosition(int index, int dimension) {
		if (dimension == 0) {
			return positions[index - getStartIndex()];
		} else {
			throw new IndexOutOfBoundsException(
					"This neuralfield specification only supports dimension 0, but was given dimension "
							+ dimension + ".");
		}
	}

	@Override
	public double getH(int dimension) {
		if (dimension == 0) {
			return h;
		} else {
			throw new IndexOutOfBoundsException(
					"This neuralfield specification only supports dimension 0, but was given dimension "
							+ dimension + ".");
		}
	}

	@Override
	public String toString(final String TOKEN, final String COMMENT) {
		String superString = super.toString();
		StringBuffer sb = new StringBuffer();
		sb.append(superString);
		sb.append(COMMENT);
		sb.append("t");
		sb.append(TOKEN);
		sb.append("theta");
		sb.append(TOKEN);
		sb.append("phi");
		sb.append(TOKEN);
		sb.append("thetaDot");
		sb.append(TOKEN);
		sb.append("phiDot");
		for (int i = 0; i < length; i++) {
			sb.append(TOKEN);
			sb.append("(");
			sb.append(i);
			sb.append(")");
		}
		sb.append("\n");
		return sb.toString();
	}
}
