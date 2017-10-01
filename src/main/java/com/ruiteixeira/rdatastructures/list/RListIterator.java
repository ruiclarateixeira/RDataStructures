package com.ruiteixeira.rdatastructures.list;

import java.util.List;
import java.util.ListIterator;

public class RListIterator implements ListIterator {
    private final List list;
    private int index = 0;

    RListIterator(List list, int index) {
        this.list = list;
        this.index = index;
    }

    RListIterator(List list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return nextIndex() < list.size();
    }

    @Override
    public Object next() {
        return list.get(nextIndex());
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public Object previous() {
        return list.get(previousIndex());
    }

    @Override
    public int nextIndex() {
        return index+1;
    }

    @Override
    public int previousIndex() {
        return index-1;
    }

    @Override
    public void remove() {
        list.remove(index);
    }

    @Override
    public void set(Object o) {
        list.set(index, o);
    }

    @Override
    public void add(Object o) {
        list.add(o);
    }
}
