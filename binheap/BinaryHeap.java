package binheap;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;

public class BinaryHeap<E> extends AbstractQueue<E> implements Queue<E> {

	E[] array;

	//@SuppressWarnings("unchecked")
	public BinaryHeap() {
		array = (E[]) new Object[10];
		// TODO Auto-generated constructor stub
	}

	public BinaryHeap(Comparator<E> cmp) {
		// TODO Auto-generated constructor stub
	}

	/**
     * Returns the number of elements in this heap.  If this heap contains more
     * than <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this collection
     */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

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

	public HeapEntry<E> insert(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	public void decreaseKey(HeapEntry<E> e, E newValue) {
		// TODO Auto-generated method stub
	}

	public void increaseKey(HeapEntry<E> e, E newValue) {
		// TODO Auto-generated method stub
	}

	public void delete(HeapEntry<E> e) {
		// TODO Auto-generated method stub
	}

	// Private helper class and methods

	//@SuppressWarnings("hiding")
	private class HeapEntry<E> {
		int pos;
		E e;

		private HeapEntry(E entry, int position) {
			pos = position;
			e = entry;
		}

		private E getEntry() { return e;}
		private int getPosition() { return pos;}
	}

	private void percolateUp(int index){
		// TODO Auto-generated method stub
	}

	private void percolateDown(int index){
		// TODO Auto-generated method stub
	}

	private void compare(E e) {
		// TODO Auto-generated method stub
	}
}
