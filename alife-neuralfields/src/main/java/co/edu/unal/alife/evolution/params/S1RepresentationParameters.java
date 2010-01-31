package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.evolution.CloneUtil;

public class S1RepresentationParameters {

	private static final double _Delta = 1d;
	private static final double _K = 2d;
	private static final double _H = 0.1;
	private List<Double> Cholesky1s;
	private List<Double> Cholesky2s;
	private List<Double> ks;
	private List<Double> Hs;
	private List<Double> deltas;
	private int prCardinality;

	private double minChoVal;
	private double maxChoVal;

	private double minKernelK;
	private double maxKernelK;
	private double minKernelDelta;
	private double maxKernelDelta;

	public S1RepresentationParameters() {
	};

	public S1RepresentationParameters(int prCardinality,double[] cholesky1s, double[] cholesky2s,
			double[] ks, double[] hs, double[] deltas) {
		super();
		this.Cholesky1s = new ArrayList<Double>();
		for (double d : cholesky1s) {
			this.Cholesky1s.add(d);
		}
		Cholesky2s = new ArrayList<Double>();
		for (double d : cholesky2s) {
			this.Cholesky2s.add(d);
		}
		this.ks = new ArrayList<Double>();
		for (double d : ks) {
			this.ks.add(d);
		}
		this.Hs = new ArrayList<Double>();
		for (double d : hs) {
			this.Hs.add(d);
		}
		this.deltas = new ArrayList<Double>();
		for (double d : deltas) {
			this.deltas.add(d);
		}
		this.prCardinality = prCardinality;
	}

	public S1RepresentationParameters(int prCardinality, double[] Chol1,
			double[] Chol2, double minKernelK, double maxKernelK,
			double minKernelDelta, double maxKernelDelta) {
		this.prCardinality = prCardinality;
		this.Cholesky1s = new ArrayList<Double>(prCardinality);
		this.Cholesky2s = new ArrayList<Double>(prCardinality);
		this.Hs = new ArrayList<Double>(prCardinality);
		this.ks = new ArrayList<Double>(prCardinality);
		this.deltas = new ArrayList<Double>(prCardinality);
		this.minKernelDelta = minKernelDelta;
		this.maxKernelDelta = maxKernelDelta;
		this.minKernelK = minKernelK;
		this.maxKernelK = maxKernelK;
		for (int i = 0; i < prCardinality; i++) {
			this.Cholesky1s.add(Chol1[i]);
			this.Cholesky2s.add(Chol2[i]);
			this.Hs.add(_H);

			double val = Math.random() * (maxKernelK - minKernelK) + minKernelK;
			this.ks.add(val);
			val = Math.random() * (maxKernelDelta - minKernelDelta)
					+ minKernelDelta;
			this.deltas.add(val);
		}

	}

	public S1RepresentationParameters(int prCardinality, double minChoVal,
			double maxChoVal) {
		super();
		this.prCardinality = prCardinality;
		this.Cholesky1s = new ArrayList<Double>(prCardinality);
		this.Cholesky2s = new ArrayList<Double>(prCardinality);
		this.Hs = new ArrayList<Double>(prCardinality);
		this.ks = new ArrayList<Double>(prCardinality);
		this.deltas = new ArrayList<Double>(prCardinality);
		for (int i = 0; i < prCardinality; i++) {
			double val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky1s.add(val);
			val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky2s.add(val);
			Hs.add(_H);
			ks.add(_K);
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
		out.minKernelK = this.minKernelK;
		out.maxKernelK = this.maxKernelK;

		out.Cholesky1s = CloneUtil.cloneAsArrayList(this.Cholesky1s);
		out.Cholesky2s = CloneUtil.cloneAsArrayList(this.Cholesky2s);
		out.Hs = CloneUtil.cloneAsArrayList(this.Hs);
		out.ks = CloneUtil.cloneAsArrayList(this.ks);
		out.deltas = CloneUtil.cloneAsArrayList(this.deltas);
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
		for (double k : this.ks) {
			sb.append(k);
			if (i++ < ks.size() - 1) {
				sb.append(",");
			}
		}
		i = 0;
		sb.append("; ");
		for (double d : this.deltas) {
			sb.append(d);
			if (i++ < deltas.size() - 1) {
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

	public List<Double> getKs() {
		return ks;
	}

	public List<Double> getHs() {
		return Hs;
	}

	public List<Double> getDeltas() {
		return deltas;
	}

	public double getMinKernelK() {
		return minKernelK;
	}

	public double getMaxKernelK() {
		return maxKernelK;
	}

	public double getMinKernelDelta() {
		return minKernelDelta;
	}

	public double getMaxKernelDelta() {
		return maxKernelDelta;
	}
}
