package co.edu.unal.alife.poc.sbw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.commons.math3.ode.sampling.FixedStepHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PocSBW3WriterStepHandler implements FixedStepHandler {
	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3WriterStepHandler.class);
	public final static String COMMENT = "% ";
	public final static String TOKEN = "\t";

	private static final String FILE_PREFIX = "SBW3_STATE_TRACE_";
	private static final String FILE_SUFFIX = ".txt";
	private String fileName;
	private FileWriter fw;
	private BufferedWriter bw;
	APocSBW3Controller controller;

	public PocSBW3WriterStepHandler(APocSBW3Controller controller) {
		super();
		fileName = FILE_PREFIX + (new Date()).toString() + FILE_SUFFIX;
		this.controller = controller;
	}

	@Override
	public void init(double t0, double[] y0, double t) {
		try {
			fw = new FileWriter(fileName, false);
			bw = new BufferedWriter(fw);
			bw.append(controller.toString(TOKEN, COMMENT));
		} catch (IOException e) {
			logger.error("Error writing init output in StepHandler", e);
			try {
				bw.close();
				fw.close();
			} catch (IOException e1) {
				logger.error("Error closing output streams in StepHandler", e1);
			}
		}
	}

	@Override
	public void handleStep(double t, double[] y, double[] yDot, boolean isLast) {
		try {
			NumberFormat format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(4);
			format.setMinimumFractionDigits(4);
			bw.append(format.format(t));
			for (int i = 0; i < y.length; i++) {
				bw.append(TOKEN);
				bw.append(format.format(y[i]));
			}
			double[] k = controller.evaluateK(t, y, PocSBW3Equation.r);
			bw.append(TOKEN);
			bw.append(format.format(k[0]));
			bw.append(TOKEN);
			bw.append(format.format(k[1]));
			bw.newLine();
			if (isLast) {
				bw.close();
				fw.close();
			}
		} catch (IOException e) {
			logger.error("Error writing event output in StepHandler", e);
			try {
				bw.close();
				fw.close();
			} catch (IOException e1) {
				logger.error("Error closing output streams in StepHandler", e1);
			}
		}
	}

}
