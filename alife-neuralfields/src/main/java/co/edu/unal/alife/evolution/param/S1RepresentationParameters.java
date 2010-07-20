package co.edu.unal.alife.evolution.param;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.evolution.impl.CloneUtil;

public class S1RepresentationParameters {

	private static final double _Delta = 1d;
	private static final double _K = 2d;
	private static final double _H = 0.1;
	private List<Double> Cholesky1s;
	private List<Double> Cholesky2s;
	private List<Double> repKs;
	private List<Double> inKs;
	private List<Double> Hs;
	private List<Double> deltas;
	private int prCardinality;

	private double minChoVal;
	private double maxChoVal;

	private double minKernelRepK;
	private double maxKernelRepK;
	private double minKernelDelta;
	private double maxKernelDelta;
	private double minKernelInK;
	private double maxKernelInK;

	public S1RepresentationParameters() {
	};

	public S1RepresentationParameters(int prCardinality,double[] cholesky1s, double[] cholesky2s,
			double[] repks, double[] hs, double[] deltas, double[] inKs) {
		super();
		this.Cholesky1s = new ArrayList<Double>();
		for (double d : cholesky1s) {
			this.Cholesky1s.add(d);
		}
		Cholesky2s = new ArrayList<Double>();
		for (double d : cholesky2s) {
			this.Cholesky2s.add(d);
		}
		this.repKs = new ArrayList<Double>();
		for (double d : repks) {
			this.repKs.add(d);
		}
		this.Hs = new ArrayList<Double>();
		for (double d : hs) {
			this.Hs.add(d);
		}
		this.deltas = new ArrayList<Double>();
		for (double d : deltas) {
			this.deltas.add(d);
		}
		this.inKs = new ArrayList<Double>();
		for (double d : inKs) {
			this.inKs.add(d);
		}
		this.prCardinality = prCardinality;
	}

	public S1RepresentationParameters(int prCardinality, double[] Chol1,
			double[] Chol2, double minKernelRepK, double maxKernelRepK,
			double minKernelDelta, double maxKernelDelta, double minKernelInK, double maxKernelInK) {
		this.prCardinality = prCardinality;
		this.Cholesky1s = new ArrayList<Double>(prCardinality);
		this.Cholesky2s = new ArrayList<Double>(prCardinality);
		this.Hs = new ArrayList<Double>(prCardinality);
		this.repKs = new ArrayList<Double>(prCardinality);
		this.deltas = new ArrayList<Double>(prCardinality);
		this.inKs = new ArrayList<Double>(prCardinality);
		this.minKernelDelta = minKernelDelta;
		this.maxKernelDelta = maxKernelDelta;
		this.minKernelRepK = minKernelRepK;
		this.maxKernelRepK = maxKernelRepK;
		this.minKernelInK = minKernelInK;
		this.maxKernelInK = maxKernelInK;
		for (int i = 0; i < prCardinality; i++) {
			this.Cholesky1s.add(Chol1[i]);
			this.Cholesky2s.add(Chol2[i]);
			this.Hs.add(_H);

			double val = Math.random() * (maxKernelRepK - minKernelRepK) + minKernelRepK;
			this.repKs.add(val);
			val = Math.random() * (maxKernelDelta - minKernelDelta)
					+ minKernelDelta;
			this.deltas.add(val);
			val = Math.random() * (maxKernelInK - minKernelInK) + minKernelInK;
			this.inKs.add(val);
		}

	}

	public S1RepresentationParameters(int prCardinality, double minChoVal,
			double maxChoVal) {
		super();
		this.prCardinality = prCardinality;
		this.Cholesky1s = new ArrayList<Double>(prCardinality);
		this.Cholesky2s = new ArrayList<Double>(prCardinality);
		this.Hs = new ArrayList<Double>(prCardinality);
		this.repKs = new ArrayList<Double>(prCardinality);
		this.deltas = new ArrayList<Double>(prCardinality);
		for (int i = 0; i < prCardinality; i++) {
			double val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky1s.add(val);
			val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky2s.add(val);
			Hs.add(_H);
			repKs.add(_K);
			deltas.add(_Delta);
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		S1RepresentationParameters out = new S1RepresentationParameters();
		out.prCardinality = this.prCardinality;
		out.minChoVal = this.minChoVal;
		out.maxChoVal = this.maxChoVal;
		out.minKernelDelta = this.minKernelDelta;
		out.maxKernelDelta = this.maxKernelDelta;
		out.minKernelRepK = this.minKernelRepK;
		out.maxKernelRepK = this.maxKernelRepK;
		out.minKernelInK = this.minKernelInK;
		out.maxKernelInK = this.maxKernelInK;

		out.Cholesky1s = CloneUtil.cloneAsArrayList(this.Cholesky1s);
		out.Cholesky2s = CloneUtil.cloneAsArrayList(this.Cholesky2s);
		out.Hs = CloneUtil.cloneAsArrayList(this.Hs);
		out.repKs = CloneUtil.cloneAsArrayList(this.repKs);
		out.deltas = CloneUtil.cloneAsArrayList(this.deltas);
		out.inKs = CloneUtil.cloneAsArrayList(this.inKs);
		return out;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		// for (double c1 : Cholesky1s) {
		// sb.append(c1);
		// if (i++ < Cholesky1s.size() - 1) {
		// sb.append(",");
		// }
		// }
		// sb.append("; ");
		// i = 0;
		// for (double c2 : Cholesky2s) {
		// sb.append(c2);
		// if (i++ < Cholesky2s.size() - 1) {
		// sb.append(",");
		// }
		// }
		// i = 0;
		// sb.append("; ");
		sb.append("repKs: ");
		for (double k : this.repKs) {
			sb.append(k);
			if (i++ < repKs.size() - 1) {
				sb.append(",");
			}
		}
		i = 0;
		sb.append("; ");
		sb.append("deltas: ");
		for (double d : this.deltas) {
			sb.append(d);
			if (i++ < deltas.size() - 1) {
				sb.append(",");
			}
		}
		i = 0;
		sb.append("; ");
		sb.append("inKs: ");
		for (double d : this.deltas) {
			sb.append(d);
			if (i++ < inKs.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public List<Double> getCholesky1s() {
		return Cholesky1s;
	}

	public List<Double> getCholesky2s() {
		return Cholesky2s;
	}

	public int getPrCardinality() {
		return prCardinality;
	}

	public double getMinChoVal() {
		return minChoVal;
	}

	public double getMaxChoVal() {
		return maxChoVal;
	}

	public List<Double> getRepKs() {
		return repKs;
	}

	public List<Double> getHs() {
		return Hs;
	}

	public List<Double> getDeltas() {
		return deltas;
	}

	public List<Double> getInKs() {
		return inKs;
	}

	public double getMinKernelRepK() {
		return minKernelRepK;
	}

	public double getMaxKernelRepK() {
		return maxKernelRepK;
	}

	public double getMinKernelDelta() {
		return minKernelDelta;
	}

	public double getMaxKernelDelta() {
		return maxKernelDelta;
	}

	public double getMinKernelInK() {
		return minKernelInK;
	}

	public double getMaxKernelInK() {
		return maxKernelInK;
	}
	
	
}
