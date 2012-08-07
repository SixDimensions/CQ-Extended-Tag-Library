/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.util;

import java.util.Iterator;

/**
 * An iterator which is also iterable, seems kind of obvious, but Java can be
 * weird.
 * 
 * @author dklco
 * 
 * @param <E>
 *            the type contained in the iterator, can be any Java Object
 */
public class IterableIterator<E> implements Iterator<E>, Iterable<E> {
	private final Iterator<E> data;

	/**
	 * Construct a new Iterable Iterator from the specified iterator.
	 * 
	 * @param data
	 *            the iterator to wrap
	 */
	public IterableIterator(Iterator<E> data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return this.data.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return this.data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next() {
		return this.data.next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		this.data.remove();
	}

}
