package co.edu.unal.alife.evolution.impl;

import java.util.ArrayList;
import java.util.List;

public class CloneUtil {
	public static <T> List<T> cloneAsArrayList(List<T> in) {
		ArrayList<T> out = new ArrayList<T>(in.size());
		for (T k : in) {
			out.add(k);
		}
		return out;
	}
}
