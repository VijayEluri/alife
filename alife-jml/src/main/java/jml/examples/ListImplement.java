package jml.examples;

import java.util.LinkedList;
import java.util.List;



public class ListImplement {
	
	/**
	 * List
	 */
	private LinkedList linkedList;
	
    /**
     * Create new list
     *
     */
	public ListImplement() {
		linkedList = new LinkedList();
	}
    
	/**
	 * Add to From
	 * @param n data
	 */
	public void addToFront(Object n) {
		linkedList.addFirst(n);
	}

	/**
	 * Add to Back
	 * @param n data
	 */
	public void addToBack(Object n) {
		linkedList.addLast(n);
	}

	/**
	 * Add to from list
	 * @param list Data
	 */
	public void addToFront(List list) {
		for (int i = 0; i < list.size(); i++) {
			addToFront(list.get(list.size() - 1 - i));
		}
	}

	/**
	 * Add to back
	 * @param list data
	 */
	public void addToBack(List list) {
		for (int i = 0; i < list.size(); i++) {
			addToBack(list.get(i));
		}
	}

	/**
	 * Remove first element
	 * @return data
	 */
	public Object removeFirst() {
		return (linkedList.removeFirst());
	}

	/**
	 * Remove last element
	 * @return data
	 */
	public Object removeLast() {
		return (linkedList.removeLast());
	}

	/**
	 * Gets first data
	 * @return data
	 */
	public Object getFirst() {
		return (linkedList.getFirst());
	}

	/**
	 * Gets last data
	 * @return data
	 */
	public Object getLast() {
		return (linkedList.getLast());
		
	}
	
	/**
	 * Gets last data
	 * @param num position
	 * @return The last data
	 */
	public Object get(int num) {
		return (linkedList.get(num));
		
	}
    
	/**
	 * Empty list
	 * @return list
	 */
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	/**
	 * Return size list
	 * @return size
	 */
	public int size() {
		return linkedList.size();
	}
    
	/**
	 * Gets list
	 * @return list
	 */
	public List asList() {
		return linkedList;
	}
	
	
}
