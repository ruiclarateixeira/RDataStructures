package com.ruiteixeira.rdatastructures.list;

import java.util.*;

public class RArrayList<T> implements List<T> {
    private T[] contents;

    RArrayList() {
        this.contents = (T[]) new Object[0];
    }

    RArrayList(T... arr) {
        this.contents = arr;
    }

    public int size() {
        return getContents().length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        return containsAll(Collections.singleton(o));
    }

    public Iterator<T> iterator() {
        return new RListIterator(this);
    }

    public Object[] toArray() {
        return getContents();
    }

    public boolean add(T o) {
        T[] newContents = (T[]) new Object[getContents().length+1];
        System.arraycopy(this.getContents(), 0, newContents, 0, this.getContents().length);
        newContents[newContents.length - 1] = o;
        this.contents = newContents;
        return true;
    }

    public boolean remove(Object o) {
        return removeAll(Collections.singleton(o));
    }

    public boolean addAll(Collection c) {
        return addAll(getContents().length, c);
    }

    public boolean addAll(int index, Collection c) {
        if(c.size() == 0)
            return true;

        T[] newContents = (T[]) new Object[getContents().length + c.size()];
        T[] newItems = (T[]) c.toArray();

        System.arraycopy(getContents(), 0, newContents, 0, index);
        System.arraycopy(newItems, 0, newContents, index, newItems.length);
        System.arraycopy(getContents(), index, newContents, index + newItems.length, getContents().length - index);
        this.contents = newContents;
        return true;
    }

    public void clear() {
        this.contents = (T[]) new Object[0];
    }

    public T get(int index) {
        return getContents()[index];
    }

    public T set(int index, Object element) {
        return this.contents[index] = (T) element;
    }

    public void add(int index, Object element) {
        addAll(index, Collections.singleton(element));
    }

    public T remove(int index) {
        T o = getContents()[index];
        remove(o);
        return o;
    }

    public int indexOf(Object o) {
        for(int index = 0; index < getContents().length; index++)
            if(getContents()[index].equals(o))
                return index;
        return -1;
    }

    public int lastIndexOf(Object o) {
        for(int index = getContents().length-1; index >= 0 ; index--)
            if(getContents()[index].equals(o))
                return index;
        return -1;
    }

    public ListIterator<T> listIterator() {
        return new RListIterator(this);
    }

    public ListIterator<T> listIterator(int index) {
        return new RListIterator(this, index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        if(fromIndex > toIndex)
            throw new RuntimeException(String.format("Bad inputs from: %d, to: %f", fromIndex, toIndex));
        T[] newContents = (T[]) new Object[toIndex-fromIndex];
        System.arraycopy(getContents(), fromIndex, newContents, 0, newContents.length);
        return new RArrayList<>(newContents);
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        T[] newContents = (T[]) new Object[getContents().length - c.size()];
        int offset = 0;
        for (int index = 0; index < this.getContents().length; index++) {
            if (c.contains(getContents()[index])) {
                offset++;
            } else
                newContents[index-offset] = getContents()[index];
        }
        this.contents = newContents;
        return true;
    }

    public boolean containsAll(Collection c) {
        int foundCount = 0;
        for(T o : getContents())
            if(c.contains(o))
                foundCount++;

        return foundCount == c.size();
    }

    public T[] toArray(Object[] a) {
        return getContents();
    }

    private T[] getContents() {
        return contents;
    }
}
