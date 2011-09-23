package binheap;

import java.util.*;

public class BinaryHeap<E> extends AbstractQueue<E> implements Queue<E> {

	Comparator<E> cmp;
	HeapEntry<E>[] array;
	int size;

	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		array = (HeapEntry<E>[]) new Object[10];
		size = 0;
		cmp = null;
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
		int pos = e.position;
		swap(array[pos], array[--size]);
		percolateDown(pos);
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
		percolateUp(size++);
		return true;
	}

	@Override
	public E peek() {
		return (size == 0) ? null : array[0].entry;
	}

	@Override
	public E poll() {
		swap(array[0], array[--size]);
		percolateDown(0);
		return array[size].entry;
	}

	/**
	 * Returns an iterator over the elements in this queue. The iterator
	 * does not return the elements in any particular order.
	 *
	 * @return an iterator over the elements in this queue
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Inserts E object e to the heap.
	 * 
	 * @param e The Item to be added to the heap
	 * @return The HeapEntry of the inserted item
	 */
	public HeapEntry<E> insert(E e) {
		if (size == array.length) {
			reallocate();
		}
		HeapEntry<E> newEntry = new HeapEntry<E>(e, size);
		array[size] = newEntry;
		percolateUp(size);
		size++;
		return newEntry;
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
		if (compare(e.entry, newValue) > 0) {
			e.entry = newValue;
			percolateUp(e.position);
		} else {
			throw new IllegalArgumentException(); 
		}
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
		if (compare(e.entry, newValue) < 0) {
			e.entry = newValue;
			percolateDown(e.position);
		} else {
			throw new IllegalArgumentException(); 
		}
	}

	// Private helper classes and methods

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
			int parentIndex = (index-1)/2;
			HeapEntry<E> child = array[index];
			HeapEntry<E> parent = array[parentIndex];
			if (compare(child.entry, parent.entry) < 0) {
				swap(parent, child);
				percolateUp(parentIndex);
			}
		}
	}

	/**
	 * Internal auxiliary method to percolate item down the heap.
	 * @param {code index} the index at which the percolate starts
	 */
	private void percolateDown(int index) {
		int childIndex1 = index*2+1;
		int childIndex2 = childIndex1 + 1;
		int childIndex0 = 0;
		HeapEntry<E> child = null;
		
		if (childIndex1 < size) {
			child = array[childIndex0 = childIndex1];
		}
		if (childIndex2 < size &&
				compare(array[childIndex1].entry,
						array[childIndex2].entry) > 0) {
			child = array[childIndex0 = childIndex2];
		}
		
		if (child != null) {
			HeapEntry<E> parent = array[index];
			if (compare(parent.entry, child.entry) > 0) {
				swap(parent, child);
				percolateDown(childIndex0);
			}
		}
	}

	private void swap(HeapEntry<E> e1, HeapEntry<E> e2) {
		int index1 = e1.position;
		int index2 = e2.position;
		e1.position = index2;
		e2.position = index1;
		array[index1] = e2;
		array[index2] = e1;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, size*2);
	}

	@SuppressWarnings("unchecked") 
	private int compare(E e1, E e2) {
		return (cmp==null) ? ((Comparable<E>) e1).compareTo(e2) : cmp.compare(e1, e2);
	}

	private class Itr implements Iterator<E> {
		int cursor = 0;
		boolean edited = false;

		/**
		 * Returns <tt>true</tt> if the iteration has more elements. (In other
		 * words, returns <tt>true</tt> if <tt>next</tt> would return an element
		 * rather than throwing an exception.)
		 *
		 * @return <tt>true</tt> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration.
		 * @exception NoSuchElementException iteration has no more elements.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[cursor++].entry;
		}

		/**
		 * Removes from the underlying collection the last element returned by the
		 * iterator (optional operation).  This method can be called only once per
		 * call to <tt>next</tt>.  The behavior of an iterator is unspecified if
		 * the underlying collection is modified while the iteration is in
		 * progress in any way other than by calling this method.
		 *
		 * @exception UnsupportedOperationException if the <tt>remove</tt>
		 *		  operation is not supported by this Iterator.
		 *
		 * @exception IllegalStateException if the <tt>next</tt> method has not
		 *		  yet been called, or the <tt>remove</tt> method has already
		 *		  been called after the last call to the <tt>next</tt>
		 *		  method.
		 */
		@Override
		public void remove() {
			if (!edited && cursor <= size) {
				delete(array[--cursor]);
				edited = true;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
