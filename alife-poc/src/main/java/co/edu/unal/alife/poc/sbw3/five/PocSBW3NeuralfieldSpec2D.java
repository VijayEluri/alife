package co.edu.unal.alife.poc.sbw3.five;

import static java.lang.Math.max;
import static java.lang.Math.min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.PocNeuralfieldsHomogeneousEquation2D;

public class PocSBW3NeuralfieldSpec2D extends APocNeuralfieldSpec {
	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3NeuralfieldSpec2D.class);
	private static final int DIMENSIONS = 2;
	private static final double START_XPOSITION = 0;
	private static final double START_YPOSITION = 0;
	private static final double END_XPOSITION = 1;
	private static final double END_YPOSITION = 1;
	private static final double SPAN_THETA = 0.4;
	private static final double SPAN_THETADOT = 0.4;

	private double hX;
	private double hY;
	/**
	 * positions[indexThetaDot][indexTheta]
	 */
	private double[][] positions;
	private int xLength;
	private int yLength;

	public PocSBW3NeuralfieldSpec2D(int startIndex, int xLength, int yLength) {
		super(startIndex, xLength * yLength);
		this.xLength = xLength;
		this.yLength = yLength;
		this.positions = new double[DIMENSIONS][getLength()];
		this.hX = (END_XPOSITION - START_XPOSITION) / getxLength();
		this.hY = (END_YPOSITION - START_YPOSITION) / getyLength();
		this.inputEquation = new PocSBW3NeuralfieldsInputEquation2D(this);
		this.outputEquation = new PocSBW3NeuralfieldsOutputEquation2DLearning(this);
		this.homogeneousEquation = new PocNeuralfieldsHomogeneousEquation2D(
				this);
		initPositions();
	}

	protected void initPositions() {
		// There are as many bins as there are positions, all of equal size.
		// And the position of each bin is its center.
		double currentXPosition = START_XPOSITION + hX / 2;
		int ii = 0;
		for (int i = 0; i < getxLength(); i++) {
			double currentYPosition = START_YPOSITION + hY / 2;
			for (int j = 0; j < getyLength(); j++) {
				positions[0][ii] = currentXPosition;
				positions[1][ii] = currentYPosition;
				currentYPosition += hY;
				ii++;
			}
			currentXPosition += hX;
		}
	}

	@Override
	public double getPosition(int index, int dimension) {
		if (dimension < 2) {
			return positions[dimension][index - getStartIndex()];
		} else {
			throw new IndexOutOfBoundsException(
					"This neuralfield specification only supports dimensions up to 1 (ie. 0 and 1), but was given dimension "
							+ dimension + ".");
		}
	}

	@Override
	public double getH(int dimension) {
		switch (dimension) {
		case 0:
			return hX;
		case 1:
			return hY;
		default:
			throw new IndexOutOfBoundsException(
					"This neuralfield specification only supports dimensions up to 1 (ie. 0 and 1), but was given dimension "
							+ dimension + ".");
		}
	}

	@Override
	public void init(double t0, double[] y0) {
		// TODO: Fix on-going
		double theta = y0[0];
		double thetaDot = y0[2];
		int xIndex = (int) ((-thetaDot / SPAN_THETADOT) * getxLength());
		int yIndex = (int) ((theta / SPAN_THETA) * getyLength());
		xIndex = min(max(xIndex, 0), getxLength() - 1);
		yIndex = min(max(yIndex, 0), getyLength() - 1);
		int index = startIndex + xIndex * getxLength() + yIndex;
		y0[index] = 1;
		logger.debug("State init with: {}",y0);
	}

	public int getxLength() {
		return xLength;
	}

	public int getyLength() {
		return yLength;
	}

	@Override
	public String toString(final String TOKEN, final String COMMENT) {
		// TODO: Put this logic in WriterStepHandler
		String superString = super.toString(TOKEN, COMMENT);
		StringBuffer sb = new StringBuffer();
		sb.append(superString);
		sb.append(COMMENT);
		sb.append("\n");
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
		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {
				sb.append(TOKEN);
				sb.append("(");
				sb.append(i);
				sb.append(",");
				sb.append(j);
				sb.append(")");
			}
		}
		sb.append(TOKEN);
		sb.append("k[0]");
		sb.append(TOKEN);
		sb.append("k[1]");
		sb.append("\n");
		return sb.toString();
	}

}
