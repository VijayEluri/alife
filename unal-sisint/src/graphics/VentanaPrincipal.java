package graphics;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import solver.Tracer;

public class VentanaPrincipal extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2281876608242374438L;
	/**
	 * @author  Juan José Figueredo Uribe
	 * @version 1.0, 22/11/15
	 */

	final Circles circles= new Circles();
	final Grafica grafica = new Grafica();
	final JPanel animadoInf = new JPanel();
	final JPanel animadoSup = new JPanel();
	final int MAX_FPS=40;
	int tics=-1;
	boolean congelado=false;
	Timer timer;
	Tracer tracer;

	//Constructor
	public VentanaPrincipal(Tracer tracer){
		// Crea el marco
		super("GUI");
		this.tracer = tracer;
		setSize(700,700);
		setResizable(false);

		//Crea el temporizador que controla las animaciones
		timer = new Timer(1000/MAX_FPS,this);
		timer.setInitialDelay(0);
		timer.setCoalesce(true);

		// Centra el marco
		Dimension tamañoDelFrame = getSize();
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tamañoPantalla.width-tamañoDelFrame.width)/2,
				(tamañoPantalla.height-tamañoDelFrame.height)/2);

		// Obtiene el contenedor
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));

		// Crea el panel izquierdo
		final JPanel panelIzq = new JPanel();
		panelIzq.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5,25,5,25),
						BorderFactory.createTitledBorder("Crear Rect�ngulo")),
						BorderFactory.createEmptyBorder(5,30,10,30)));
		panelIzq.setLayout(new GridLayout(26,1));

		// Crea el panel derecho
		final JPanel panelDer = new JPanel();
		panelDer.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.DARK_GRAY));
		panelDer.setLayout(new GridLayout(2,1));

		// Especifica el subpanel superior de animaci�n y lo a�ade al panel derecho
		animadoSup.setBackground(Color.white);
		panelDer.add(animadoSup);

		// Especifica el subpanel inferior de animaci�n y lo a�ade al panel derecho
		animadoInf.setBackground(Color.white);
		panelDer.add(animadoInf);

		// Crea las etiquetas para el p�nel izquierdo
		final JLabel propiedadesLabel = new JLabel("- PROPIEDADES -",JLabel.CENTER);
		final JLabel textTaoLabel = new JLabel("Tiempo Caracter�stico (Tao>0.1)");
		final JLabel textOmegaLabel = new JLabel("Frecuencia Natural (Omega<10)");
		final JLabel textHLabel = new JLabel("Tiempo de muestreo (h>"+1000/MAX_FPS+" ms)");
		final JLabel textTfLabel = new JLabel("Tiempo Final (tf<=20000 ms)");
		final JLabel textJJFULabel = new JLabel("Desarrollado por: Juan J. Figueredo U.");

		// Crea los campos de texto para la transformaci�n
		final JTextField textTao = new JTextField("5");
		final JTextField textOmega = new JTextField("3.14159");
		final JTextField textH = new JTextField("50");
		final JTextField textTf = new JTextField("20000");

		// Crea el bot�n Aplicar
		final JButton buttonAplicar = new JButton("Ejecutar");

		// Asigna el manejador de eventos para el bot�n Aplicar
		buttonAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//float tao = Float.parseFloat(textTao.getText());
					//float omega = Float.parseFloat(textOmega.getText());
					//float h = Float.parseFloat(textH.getText());
					//float tf = Float.parseFloat(textTf.getText());
					//if(tf>20000) {
					//	tf=20000;
					//}
					//if(h<1000/MAX_FPS) {
					//	h=1000/MAX_FPS;
					//}
					timer.setDelay(25);
					//if(tao<0.1 || omega>10)
					//	throw new Exception();
					circles.animar((int)25,(int)10000);
					grafica.animar((int)25,(int)10000);
					animadoSup.repaint();
					iniciarAnimacion();
				} catch (Exception ex) {}
			}
		});

		// Agrega los elementos al panel izquierdo
		panelIzq.add(propiedadesLabel);
		panelIzq.add(textTaoLabel);
		panelIzq.add(textTao);
		panelIzq.add(textOmegaLabel);
		panelIzq.add(textOmega);
		panelIzq.add(textHLabel);
		panelIzq.add(textH);
		panelIzq.add(textTfLabel);
		panelIzq.add(textTf);
		panelIzq.add(new JLabel());
		panelIzq.add(buttonAplicar);
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(textJJFULabel);

		// Agrega los p�neles al contenedor principal
		contentPane.add(panelIzq,BorderLayout.EAST);
		contentPane.add(panelDer,BorderLayout.WEST);

		// Crea el manejador de eventos de la ventana
		addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				detenerAnimacion();
			}
			public void windowDeiconified(WindowEvent e) {
				iniciarAnimacion();
			}
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		show();
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
		//System.out.println(tics);
		if(grafica.count*grafica.h<grafica.tf){
			grafica.actualizar();
			SwingUtilities.invokeLater(grafica);
		}
		if(circles.count*circles.h<circles.tf) {
			circles.actualizar();
			SwingUtilities.invokeLater(circles);
		} else {
			detenerAnimacion();
		}
	}



	public class Circles implements Runnable
	{
		int frameNumber = -1;
		Timer timer;
		JPanel jP;
		Graphics2D g2d;
		float posicionX, posicionY, posicionX2, posicionY2;
		int count, h, tf;
		float dOx, dOx2, dOy, dOy2, dx, dx2, dy, dy2, prevX, prevY, prevX2, prevY2, prevOx, prevOy;

		public Circles() {
			count=0;
			this.posicionX=200;
			this.posicionY=150;
			this.posicionX2=200;
			this.posicionY2=150;
		}

		public void animar(int h, int tf){
			g2d = (Graphics2D) animadoInf.getGraphics();
			this.h=h;
			this.tf=tf;
			count=0;
		}

		public void run() {
			g2d.setColor(Color.WHITE);
			g2d.draw(new Ellipse2D.Float(posicionX+prevX,posicionY+prevY,20,20));
			g2d.draw(new Line2D.Float(posicionX+prevX+10,posicionY+prevY+10,posicionX+prevOx+10,posicionY+prevOy+10));
			g2d.setColor(Color.WHITE);
			Ellipse2D e2dprev = new Ellipse2D.Float(posicionX2+prevX2,posicionY2+prevY2,20,20);
			g2d.draw(e2dprev);
			g2d.setPaint(Color.WHITE);
			g2d.fill(e2dprev);
			g2d.setColor(Color.BLUE);
			g2d.draw(new Ellipse2D.Float(posicionX+dx,posicionY+dy,20,20));
			g2d.draw(new Line2D.Float(posicionX+dx+10,posicionY+dy+10,posicionX+dOx+10,posicionY+dOy+10));
			Ellipse2D e2d = new Ellipse2D.Float(posicionX2+dx2,posicionY2+dy2,20,20);
			g2d.setColor(Color.RED);
			g2d.draw(e2d);
			g2d.setPaint(Color.RED);
			g2d.fill(e2d);
		}

		public void actualizar(){
			count++;
			prevX=dx;
			prevX2=dx2;
			prevY=dy;
			prevY2=dy2;
			prevOx=dOx;
			prevOy=dOy;
			double[] data = tracer.getData().elementAt(count-1);
			dy = -(float)data[1]*100;
			dx = (float)data[0]*100;
			dy2 = -(float)data[7]*100;
			dx2 = (float)data[6]*100;
			dOx = (float)(dx+10*Math.cos(data[2]));
			dOy = (float)(dy-10*Math.sin(data[2]));
			//if (count<399) {
			/*}
   else
       dx= 0;*/
		}
	}

	public class Grafica implements Runnable
	{
		int frameNumber = -1;
		Timer timer;
		JPanel jP;
		Graphics2D g2d;
		float posicionX, posicionY, posicionX2, posicionY2;
		int count, h, tf;
		float dx,dy,dx2,dy2;

		public Grafica() {
			count=0;
			dx=0;
			this.posicionX=200;
			this.posicionY=150;
			this.posicionX2=200;
			this.posicionY2=150;
		}

		public void animar(int h, int tf){
			g2d = (Graphics2D) animadoSup.getGraphics();
			g2d.setColor(Color.RED);
			this.h=h;
			this.tf=tf;
			count=0;
		}

		public void run() {
			g2d.setColor(Color.BLUE);
			g2d.draw(new Rectangle2D.Float(posicionX+dx,posicionY+dy,1,1));
			g2d.setColor(Color.RED);
			g2d.draw(new Rectangle2D.Float(posicionX2+dx2,posicionY2+dy2,1,1));
		}

		public void actualizar(){
			count++;
			//if (count<400) {
			double[] data = tracer.getData().elementAt(count-1);
			//System.out.println("data: "+MatrixOperator.toString(data));
			dy = -(float)data[1]*100;
			dx = (float)data[0]*100;
			dy2 = -(float)data[7]*100;
			dx2 = (float)data[6]*100;
			/*} else {
       		dy= 0;
   			}*/
		}

	}
	
	public static void main (String[] args){
		//VentanaPrincipal vp = new VentanaPrincipal();
	}
}
