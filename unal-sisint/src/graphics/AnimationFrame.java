package graphics;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import solver.Tracer;
import gait.InvertedPendulum;

public class AnimationFrame extends JFrame implements ActionListener
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
	public AnimationFrame(Tracer tracer){
		
		super("InvertedPendulum GUI");
		this.tracer = tracer;
		setSize(1000,700);
		setResizable(false);


		timer = new Timer(1000/MAX_FPS,this);
		timer.setInitialDelay(0);
		timer.setCoalesce(true);

		
		Dimension tamañoDelFrame = getSize();
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tamañoPantalla.width-tamañoDelFrame.width)/2,
				(tamañoPantalla.height-tamañoDelFrame.height)/2);

		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(3,1));

		
		final JPanel panelIzq = new JPanel();
		panelIzq.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5,25,5,25),
						BorderFactory.createTitledBorder("Controls")),
						BorderFactory.createEmptyBorder(5,30,10,30)));
		panelIzq.setLayout(new GridLayout(10,1));

		
		final JPanel panelDer = new JPanel();
		panelDer.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.DARK_GRAY));
		panelDer.setLayout(new GridLayout(1,1));
		
		final JPanel panelDer1 = new JPanel();
		panelDer1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.DARK_GRAY));
		panelDer1.setLayout(new GridLayout(1,1));

		
		animadoSup.setBackground(Color.white);
		animadoSup.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.DARK_GRAY));
		panelDer.add(animadoSup);

		
		animadoInf.setBackground(Color.white);
		animadoInf.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.DARK_GRAY));
		panelDer1.add(animadoInf);

		final JLabel textJJFULabel = new JLabel("Developed by: Juan J. Figueredo U.");

		final JButton buttonAplicar = new JButton("Animate");

		buttonAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					timer.setDelay(25);
					circles.animar((int)25,(int)10000);
					grafica.animar((int)25,(int)10000);
					animadoSup.repaint();
					iniciarAnimacion();
				} catch (Exception ex) {}
			}
		});

		panelIzq.add(new JLabel());
		panelIzq.add(buttonAplicar);
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(new JLabel());
		panelIzq.add(textJJFULabel);

		
		contentPane.add(panelIzq,BorderLayout.NORTH);
		contentPane.add(panelDer,BorderLayout.CENTER);
		contentPane.add(panelDer1,BorderLayout.SOUTH);

		
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
		float dxr, dx, dx2, dy, dy2, prevX, prevY, prevX2, prevY2, prevXr;

		public Circles() {
			count=0;
			this.posicionX=500;
			this.posicionY=100;
			this.posicionX2=500;
			this.posicionY2=100;
		}

		public void animar(int h, int tf){
			g2d = (Graphics2D) animadoInf.getGraphics();
			this.h=h;
			this.tf=tf;
			count=0;
		}

		public void run() {
			g2d.setColor(Color.WHITE);
			g2d.draw(new Ellipse2D.Float(posicionX+prevX,posicionY+prevY,10,10));
			g2d.draw(new Ellipse2D.Float(posicionX2+prevX2,posicionY2+prevY2,10,10));
			g2d.draw(new Line2D.Float(posicionX+prevX+5,posicionY+prevY+5,posicionX2+prevX2+5,posicionY2+prevY2+5));
			g2d.draw(new Ellipse2D.Float(posicionX+prevXr,posicionY+20,10,10));
			g2d.setColor(Color.BLUE);
			g2d.draw(new Ellipse2D.Float(posicionX+dx,posicionY+dy,10,10));
			g2d.draw(new Ellipse2D.Float(posicionX2+dx2,posicionY2+dy2,10,10));
			g2d.draw(new Line2D.Float(posicionX+dx+5,posicionY+dy+5,posicionX2+dx2+5,posicionY2+dy2+5));
			g2d.setColor(Color.RED);
//			g2d.draw(new Ellipse2D.Float(posicionX-400,posicionY,10,10));
//			g2d.draw(new Ellipse2D.Float(posicionX+400,posicionY,10,10));
			g2d.draw(new Line2D.Float(posicionX-400,posicionY+5,posicionX+400,posicionY+5));
			g2d.setColor(Color.GREEN);
			g2d.draw(new Ellipse2D.Float(posicionX+dxr,posicionY+20,10,10));
		}

		public void actualizar(){
			count++;
			prevX=dx;
			prevX2=dx2;
			prevY=dy;
			prevY2=dy2;
			prevXr=dxr;
			double[] data = tracer.getData().elementAt(count-1);
			dy = 0;
			dx = (float)(data[0])*50;
			dy2 = -(float)cos(data[1])*50;
			dx2 = (float)(data[0]+sin(data[1]))*50;
//			dxr = (float)InvertedPendulum.rFun((count-1)*0.025)*50;
			dxr = (float)0.0*50;
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
			this.posicionX=500;
			this.posicionY=100;
			this.posicionX2=500;
			this.posicionY2=100;
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
			dy = 0;
			dx = (float)(data[0])*50;
			dy2 = -(float)cos(data[1])*50;
			dx2 = (float)(data[0]+sin(data[1]))*50;
			/*} else {
       		dy= 0;
   			}*/
		}

	}
	
	public static void main (String[] args){
		//VentanaPrincipal vp = new VentanaPrincipal();
	}
}
