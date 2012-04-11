package co.edu.unal.alife.poc.sbw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.commons.math3.ode.events.EventHandler;
import org.w3c.dom.events.EventException;

public class PocSBW3WriterSwitchHandler extends PocSBW3SwitchHandler implements
		EventHandler {
	public final static String COMMENT = "% ";
	public final static String TOKEN = "\t";

	private static final String FILE_PREFIX = "SBW3_SWITCH_TRACE_";
	private static final String FILE_SUFFIX = ".txt";

	private String fileName;
	private FileWriter fw;
	private BufferedWriter bw;

	public PocSBW3WriterSwitchHandler(PocSBW3Equation equation,
			APocSBW3Controller controller) {
		super(equation, controller);
		fileName = FILE_PREFIX + (new Date()).toString() + FILE_SUFFIX;
	}

	@Override
	public void init(double t0, double[] y0, double t) {
		super.init(t0, y0, t);
		try {
			fw = new FileWriter(fileName, false);
			bw = new BufferedWriter(fw);
			appendComment();
			appendEvent(t0, y0);
			bw.flush();
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
	public void resetState(double t, double[] y) throws EventException {
		super.resetState(t, y);
		try {
			appendEvent(t, y);
			bw.flush();
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

	public void appendComment() throws IOException {
		bw.append(COMMENT);
		bw.append("t");
		bw.append(TOKEN);
		bw.append("theta+");
		bw.append(TOKEN);
		bw.append("phi+");
		bw.append(TOKEN);
		bw.append("thetaDot+");
		bw.append(TOKEN);
		bw.append("phiDot+");
		bw.append(TOKEN);
		bw.append("k[0]");
		bw.append(TOKEN);
		bw.append("k[1]");
		bw.newLine();
	}

	public void appendEvent(double t, double[] y) throws IOException {
		double[] k = controller.evaluateK(t, y, PocSBW3Equation.r);
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(4);
		format.setMinimumFractionDigits(4);
		bw.append(format.format(t));
		for (int i = 0; i < PocSBW3Equation.Q_DIMENSION; i++) {
			bw.append(TOKEN);
			bw.append(format.format(y[i]));
		}
		for (int i = 0; i < k.length; i++) {
			bw.append(TOKEN);
			bw.append(format.format(k[i]));
		}
		bw.newLine();
	}

}
