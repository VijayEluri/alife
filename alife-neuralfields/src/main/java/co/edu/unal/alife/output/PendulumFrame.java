package co.edu.unal.alife.output;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.pendulum.PendulumEquation;

public class PendulumFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2281876608242374438L;
	/**
	 * @author jjfigueredou
	 */
	
	int pendulumPopIndex = 2;
	int repPopIndex = 1;
	int inputPopIndex = 0;

	final Pendulum pendulum = new Pendulum();
	final PendulumTrace pendulumTrace = new PendulumTrace();

	final JPanel animPendulum = new JPanel();
	final JPanel animHiddenField = new JPanel();
	final JPanel animPendulumTrace = new JPanel();
	final JPanel animInputField = new JPanel();
	final Field inputField = new Field(0,animInputField);
	final Field hiddenField = new Field(1,animHiddenField);

	final int MAX_FPS = 40;
	int tics = -1;
	boolean congelado = false;
	Timer timer;
	Tracer tracer;

	public PendulumFrame(int inputPopIndex, int pendulumPopIndex,
			int repPopIndex, Tracer tracer) throws HeadlessException {
		super("InvertedPendulum GUI");
		this.tracer = tracer;
		this.inputPopIndex = inputPopIndex;
		this.pendulumPopIndex = pendulumPopIndex;
		this.repPopIndex = repPopIndex;
		createFrame();
	}

	// Constructor
	public PendulumFrame(Tracer tracer) {
		super("InvertedPendulum GUI");
		this.tracer = tracer;
		createFrame();
	}

	private void createFrame() {
		setSize(1000, 700);
		setResizable(false);

		timer = new Timer(1000 / MAX_FPS, this);
		timer.setInitialDelay(0);
		timer.setCoalesce(true);

		Dimension tamañoDelFrame = getSize();
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tamañoPantalla.width - tamañoDelFrame.width) / 2,
				(tamañoPantalla.height - tamañoDelFrame.height) / 2);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));

		final JPanel panelSup = new JPanel();
		panelSup.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createEmptyBorder(5, 25, 5,
						25), BorderFactory.createTitledBorder("Controls")),
				BorderFactory.createEmptyBorder(5, 30, 10, 30)));
		panelSup.setLayout(new GridLayout(10, 1));

		final JPanel panelMid = new JPanel();
		panelMid.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,
				Color.DARK_GRAY));
		panelMid.setLayout(new GridLayout(1, 2));

		final JPanel panelLow = new JPanel();
		panelLow.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,
				Color.DARK_GRAY));
		panelLow.setLayout(new GridLayout(1, 2));

		animInputField.setBackground(Color.white);
		animInputField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.DARK_GRAY));
		panelMid.add(animInputField);

		animPendulumTrace.setBackground(Color.white);
		animPendulumTrace.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.DARK_GRAY));
		panelLow.add(animPendulumTrace);

		animHiddenField.setBackground(Color.white);
		animHiddenField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.DARK_GRAY));
		panelMid.add(animHiddenField);

		animPendulum.setBackground(Color.white);
		animPendulum.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.DARK_GRAY));
		panelLow.add(animPendulum);

		final JLabel textJJFULabel = new JLabel(
				"Developed by: Juan J. Figueredo U.");

		final JButton buttonAplicar = new JButton("Animate");

		buttonAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int h = 1000 / MAX_FPS;
					timer.setDelay(h);
					pendulum.animar((int) h, (int) 20000);
					pendulumTrace.animar((int) h, (int) 20000);
					inputField.animar((int) h, (int) 20000);
					hiddenField.animar((int) h, (int) 20000);
					iniciarAnimacion();
				} catch (Exception ex) {
				}
			}
		});

		panelSup.add(new JLabel());
		panelSup.add(buttonAplicar);
		panelSup.add(new JLabel());
		panelSup.add(new JLabel());
		panelSup.add(new JLabel());
		panelSup.add(new JLabel());
		panelSup.add(textJJFULabel);

		contentPane.add(panelSup, BorderLayout.NORTH);
		contentPane.add(panelMid, BorderLayout.CENTER);
		contentPane.add(panelLow, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				detenerAnimacion();
			}

			public void windowDeiconified(WindowEvent e) {
				iniciarAnimacion();
			}

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	// Inicia (o contin�a) la animaci�n
	public void iniciarAnimacion() {
		if (!congelado) {
			if (!timer.isRunning()) {
				timer.start();
			}
		}
	}

	// Detiene la animaci�n
	public void detenerAnimacion() {
		if (timer.isRunning()) {
			timer.stop();
		}
	}

	// Es la acci�n ejecutada por el timer cada 1000/MAX_FRAMES milisegundos
	public void actionPerformed(ActionEvent e) {
		tics++;
		// System.out.println(tics);
		// if (grafica.count < tracer.getData().size()) {
		// grafica.actualizar();
		// SwingUtilities.invokeLater(grafica);
		// }
		if (inputField.count < tracer.getData().get(inputPopIndex).size()) {
			inputField.actualizar();
			SwingUtilities.invokeLater(inputField);
		}
		if (hiddenField.count < tracer.getData().get(repPopIndex).size()) {
			hiddenField.actualizar();
			SwingUtilities.invokeLater(hiddenField);
		}
		if (pendulum.count < tracer.getData().get(inputPopIndex).size()) {
			pendulum.actualizar();
			SwingUtilities.invokeLater(pendulum);
		}
		if (pendulumTrace.count < tracer.getData().get(pendulumPopIndex).size()) {
			pendulumTrace.actualizar();
			SwingUtilities.invokeLater(pendulumTrace);
		}
	}

	public class Pendulum implements Runnable {
		int frameNumber = -1;
		Timer timer;
		JPanel jP;
		Graphics2D g2d;
		float posicionX, posicionY, posicionX2, posicionY2;
		int count, h, tf;
		float dxr, dx, dx2, dy, dy2, prevX, prevY, prevX2, prevY2, prevXr;

		public Pendulum() {
			count = 0;
			this.posicionX = 250;
			this.posicionY = 100;
			this.posicionX2 = 250;
			this.posicionY2 = 100;
		}

		public void animar(int h, int tf) {
			g2d = (Graphics2D) animPendulum.getGraphics();
			this.h = h;
			this.tf = tf;
			count = 0;
		}

		public void run() {
			g2d.setColor(Color.WHITE);
			g2d.draw(new Ellipse2D.Float(posicionX + prevX, posicionY + prevY,
					10, 10));
			g2d.draw(new Ellipse2D.Float(posicionX2 + prevX2, posicionY2
					+ prevY2, 10, 10));
			g2d.draw(new Line2D.Float(posicionX + prevX + 5, posicionY + prevY
					+ 5, posicionX2 + prevX2 + 5, posicionY2 + prevY2 + 5));
			g2d.draw(new Ellipse2D.Float(posicionX + prevXr, posicionY + 20,
					10, 10));
			g2d.setColor(Color.BLUE);
			g2d
					.draw(new Ellipse2D.Float(posicionX + dx, posicionY + dy,
							10, 10));
			g2d.draw(new Ellipse2D.Float(posicionX2 + dx2, posicionY2 + dy2,
					10, 10));
			g2d.draw(new Line2D.Float(posicionX + dx + 5, posicionY + dy + 5,
					posicionX2 + dx2 + 5, posicionY2 + dy2 + 5));
			g2d.setColor(Color.RED);
			g2d
					.draw(new Ellipse2D.Float(posicionX - 270, posicionY - 5,
							20, 20));
			g2d
					.draw(new Ellipse2D.Float(posicionX + 250, posicionY - 5,
							20, 20));
			g2d.draw(new Line2D.Float(posicionX - 270, posicionY + 5,
					posicionX + 270, posicionY + 5));
			g2d.setColor(Color.GREEN);
			g2d.draw(new Ellipse2D.Float(posicionX + dxr, posicionY + 20, 10,
					10));
		}

		public void actualizar() {
			count++;
			prevX = dx;
			prevX2 = dx2;
			prevY = dy;
			prevY2 = dy2;
			prevXr = dxr;
			DeltaPopulation<Double> pendulumData = tracer.getData().get(
					pendulumPopIndex).get(count - 1);

			Double xValue = pendulumData
					.getElementValue(PendulumEquation.STATE_X);
			Double thetaValue = pendulumData
					.getElementValue(PendulumEquation.STATE_THETA);

			dy = 0;
			dx = xValue.floatValue() * 50;
			dy2 = -(float) cos(thetaValue) * 50;
			dx2 = (float) (xValue + sin(thetaValue)) * 50;
			// dxr = (float) InvertedPendulum.rFun((count - 1) * 0.040) * 50;
			// dxr = (float) 0.0 * 50;
			// System.out.println(count+" - "+dy2);
		}
	}

	public class Field implements Runnable {
		int frameNumber = -1;
		int deltaPopNumber;
		Timer timer;
		JPanel jP;
		Graphics2D g2d;
		float posicionX, posicionY, posicionX2, posicionY2;
		int count, h, tf;
		int fromIndex = 4 + 21;
		int toIndex = fromIndex + 21;
		List<Double> fieldValues;
		List<Double> prevFieldValues = new ArrayList<Double>();
		
		public Field(int deltaPopNumber, JPanel jP) {
			count = 0;
			this.posicionX = 250;
			this.posicionY = 100;
			this.deltaPopNumber = deltaPopNumber;
			this.jP = jP;
		}
		
		public Field(JPanel jP) {
			this(repPopIndex, jP);
		}

		public void animar(int h, int tf) {
			g2d = (Graphics2D) jP.getGraphics();
			this.h = h;
			this.tf = tf;
			count = 0;
		}

		public void run() {
			g2d.setColor(Color.WHITE);
			int i = 0;
			int fieldSize = prevFieldValues.size();
			for (Double value : prevFieldValues) {
				int dx = (int)((-fieldSize/2d + i++ )/fieldSize * 200);
				float dy = (float) value.floatValue() * 5;
				// g2d.draw(new Ellipse2D.Float(posicionX + dx, posicionY + dy,
				// 5, 5));
				g2d.draw(new Line2D.Float(posicionX + dx, posicionY, posicionX
						+ dx, posicionY - dy));
			}
			g2d.setColor(Color.BLUE);
			i = 0;
			fieldSize=fieldValues.size();
			for (Double value : fieldValues) {
				int dx = (int)((-fieldSize/2d + i++)/fieldSize * 200);
				float dy = (float) value.floatValue() * 5;
				// g2d.draw(new Ellipse2D.Float(posicionX + dx, posicionY + dy,
				// 5, 5));
				g2d.draw(new Line2D.Float(posicionX + dx, posicionY, posicionX
						+ dx, posicionY - dy));
			}
			g2d.setColor(Color.RED);
			g2d.draw(new Line2D.Float(posicionX - 270, posicionY,
					posicionX + 270, posicionY));
		}

		public void actualizar() {
			count++;
			prevFieldValues = fieldValues != null ? fieldValues
					: prevFieldValues;
			DeltaPopulation<Double> fieldData = tracer.getData().get(
					deltaPopNumber).get(count - 1);
			Set<Double> positions = fieldData.getPositions();
			fieldValues = new ArrayList<Double>();
			for (Double position : positions) {
				fieldValues.add(fieldData.getElementValue(position));
			}
		}
	}

	public class PendulumTrace implements Runnable {
		int frameNumber = -1;
		Timer timer;
		JPanel jP;
		Graphics2D g2d;
		float posicionX, posicionY, posicionX2, posicionY2;
		int count, h, tf;
		float dx, dy, dx2, dy2;

		public PendulumTrace() {
			count = 0;
			dx = 0;
			this.posicionX = 250;
			this.posicionY = 100;
			this.posicionX2 = 250;
			this.posicionY2 = 100;
		}

		public void animar(int h, int tf) {
			g2d = (Graphics2D) animPendulumTrace.getGraphics();
			g2d.setColor(Color.RED);
			this.h = h;
			this.tf = tf;
			count = 0;
		}

		public void run() {
			g2d.setColor(Color.BLUE);
			g2d
					.draw(new Rectangle2D.Float(posicionX + dx, posicionY + dy,
							1, 1));
			g2d.setColor(Color.RED);
			g2d.draw(new Rectangle2D.Float(posicionX2 + dx2, posicionY2 + dy2,
					1, 1));
		}

		public void actualizar() {
			count++;
			DeltaPopulation<Double> pendulumData = tracer.getData().get(
					pendulumPopIndex).get(count - 1);

			Double xValue = pendulumData
					.getElementValue(PendulumEquation.STATE_X);
			Double thetaValue = pendulumData
					.getElementValue(PendulumEquation.STATE_THETA);

			dy = 0;
			dx = xValue.floatValue() * 50;
			dy2 = -(float) cos(thetaValue) * 50;
			dx2 = (float) (xValue + sin(thetaValue)) * 50;
		}
	}

	public static void main(String[] args) {
		// VentanaPrincipal vp = new VentanaPrincipal();
	}
}
