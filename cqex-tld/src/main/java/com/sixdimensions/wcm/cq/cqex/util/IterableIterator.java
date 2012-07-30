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
 */
public class IterableIterator<E> implements Iterator<E>, Iterable<E> {
	private final Iterator<E> data;

	public IterableIterator(Iterator<E> data) {
		this.data = data;
	}

	@Override
	public boolean hasNext() {
		return this.data.hasNext();
	}

	@Override
	public Iterator<E> iterator() {
		return this.data;
	}

	@Override
	public E next() {
		return this.data.next();
	}

	@Override
	public void remove() {
		this.data.remove();
	}

}
