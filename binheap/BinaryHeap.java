package binheap;

import java.util.*;

public class BinaryHeap<E extends Comparable<E>> extends AbstractQueue<E> implements Queue<E> {
	
	Comparator<E> cmp;
	HeapEntry<E>[] array;
	int size;
	
	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		array = (HeapEntry<E>[]) new Object[10];
		size = 0;
		cmp = new Comp<E>();
	}
	
	public BinaryHeap(Comparator<E> cmp) {
		this();
		this.cmp = cmp; 
	}
	
	/**
	 * Deletes the specified HeapEntry object from this heap
	 * 
	 * @param e HeapEntry object to be removed
	 */
	public void delete(HeapEntry<E> e) {
		// TODO Auto-generated method stub
	}
	
	/**
     * Returns the number of elements in this heap.
     *
     * @return the number of elements in this heap
     */
	@Override
	public int size() {
		return size;
	}
	
	/**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public boolean offer(E arg0) {
		if (size == array.length) {
			reallocate();
		}
		array[size] = new HeapEntry<E>(arg0, size);
		percolateUp(size);
		size++;
		return true;
	}
	
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * Returns an iterator over the elements in this queue. The iterator
     * does not return the elements in any particular order.
     *
     * @return an iterator over the elements in this queue
     */
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Inserts E object e to the heap.
	 * 
	 * @param e The Item to be added to the heap
	 * @return The HeapEntry of the inserted item
	 */
	public HeapEntry<E> insert(E e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Changes the value of the specified HeapEntry object to
	 * newValue if the new value is less than the old value.
	 * 
	 * @param e The HeapEntry whose value is to be changed
	 * @param newValue The new value of the specified HeapEntry object
	 * @throws IllegalArgumentException if the new value is greater than the
	 * old value
	 */
	public void decreaseKey(HeapEntry<E> e, E newValue) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Changes the value of the specified HeapEntry object to
	 * newValue if the new value is greater than the old value.
	 * 
	 * @param e The HeapEntry whose value is to be changed
	 * @param newValue The new value of the specified HeapEntry object
	 * @throws IllegalArgumentException if the new value is greater than the
	 * old value
	 */
	public void increaseKey(HeapEntry<E> e, E newValue) {
		// TODO Auto-generated method stub
	}
	
	// Private helper classes and methods
	
	//@SuppressWarnings("hiding")
	private static class HeapEntry<E> {
		private int position;
		private E entry;
		
		private HeapEntry(E entry, int position) {
			this.position = position;
			this.entry = entry;
		}
	}
	
	/**
	 * Internal auxiliary method to percolate item up the heap.
	 * @param {code index} the index at which the percolate starts
	 */
	private void percolateUp(int index){
		if (index != 0) {
			E child = array[index].entry;
			E parent = array[parentIndex(index)].entry;
			if (compare(child, parent) < 0) {
				array[index].entry = child;
				array[parentIndex(index)].entry = parent;
				percolateUp(parentIndex(index));
			}
		}
	}
	
	/**
	 * Internal auxiliary method to percolate item down the heap.
	 * @param {code index} the index at which the percolate starts
	 */
	private void percolateDown(int index){
		
	}
	
	private void reallocate() {
		array = Arrays.copyOf(array, size*2);
	}
	
	private int parentIndex(int a) {
		return (a-1)/2;
	}
	
	private int compare(E e1, E e2) {
		return (cmp==null) ? e1.compareTo(e2) : cmp.compare(e1, e2);
	}
	
	private class Comp<E extends Comparable<E>> implements Comparator<E> {
		@Override
		public int compare(E o1, E o2) {
			return o1.compareTo(o2);
		}
	}
}
