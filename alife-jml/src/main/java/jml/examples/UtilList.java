package jml.examples;

import java.util.ArrayList;
import java.util.List;


public class UtilList extends ListImplement {
	
	/**
	 * Add to from
	 * @param anItem Data 
	 */
	public void addF(Object anItem) {
		super.addToFront(anItem);
	}
	
	/**
	 * Add to Back
	 * @param anItem Data
	 */
	public void addB(Object anItem) {
		super.addToBack(anItem);
	}

	/**
	 * Add list into list
	 * @param items Add list
	 */
	public void add(List items) {
		List reversed = new ArrayList();
		for (int i = items.size() - 1; i > -1; i--) {
			reversed.add(items.get(i));
		}
		super.addToFront(reversed);
	}
	
	/**
	 * Remove object
	 * @return date
	 */
	public Object remove() {
		return super.removeLast();
	}

		
	/**
	 * Return firts object
	 * @return data
	 */
	public Object getfirst() {
		return super.getFirst();
	}
	
	/**
	 * Return firts object
	 * @return data
	 */
	public Object getlast() {
		return super.getLast();
	}

}
