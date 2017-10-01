package com.ruiteixeira.rdatastructures.list;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RArrayListTest {
    @Test
    public void size() throws Exception {
        List<Integer> list = new RArrayList<>();
        assertThat(list.size(), is(0));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.size(), is(3));
    }

    @Test
    public void isEmpty() throws Exception {
        List<Integer> list = new RArrayList<>();
        assertThat(list.isEmpty(), is(true));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void contains() throws Exception {
        List<Integer> list = new RArrayList<>();
        assertThat(list.contains(1), is(false));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.contains(4), is(false));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.contains(3), is(true));
    }

    @Test
    public void iterator() throws Exception {
        List<Integer> list = new RArrayList<>();
        assert(list.iterator() instanceof RListIterator);
    }

    @Test
    public void toArray() throws Exception {
        List<Integer> list = new RArrayList<>();
        assertThat(list.toArray(), is(new Integer[] {}));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.toArray(), is(new Integer[] {1, 2, 3}));
    }

    @Test
    public void add() throws Exception {
        List<Integer> list = new RArrayList<>();
        list.add(6);
        assertThat(list.toArray(), is(new Integer[] {6}));
        list = new RArrayList<>(1, 2, 3);
        list.add(6);
        assertThat(list.toArray(), is(new Integer[] {1, 2, 3, 6}));
    }

    @Test
    public void remove() throws Exception {
        List<Integer> list = new RArrayList<>();
        try {
            list.remove(new Integer(6));
            fail("This should blow up");
        } catch(NegativeArraySizeException e) {
            assert(true);
        }
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.remove(new Integer(1)), is(true));
        assertThat(list.toArray(), is(new Integer[]{2, 3}));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.remove(new Integer(2)), is(true));
        assertThat(list.toArray(), is(new Integer[]{1, 3}));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.remove(new Integer(3)), is(true));
        assertThat(list.toArray(), is(new Integer[]{1, 2}));
    }

    @Test
    public void addAll() throws Exception {
        List<Integer> list = new RArrayList<>();
        list.addAll(Collections.emptyList());
        assertThat(list.size(), is(0) );
        list = new RArrayList<>(1, 2, 3);
        list.addAll(new RArrayList<>(6, 8));
        assertThat(list.toArray(), is(new Integer[] {1, 2, 3, 6, 8}));
    }

    @Test
    public void addAllWithIndex() throws Exception {
        List<Integer> list = new RArrayList<>();
        list.addAll(5, Collections.emptyList());
        assertThat(list.size(), is(0) );

        list = new RArrayList<>(1, 2, 3);
        list.addAll(0, new RArrayList<>(6, 8));
        assertThat(list.toArray(), is(new Integer[] {6, 8, 1, 2, 3 }));

        list = new RArrayList<>(1, 2, 3);
        list.addAll(1, new RArrayList<>(6, 8));
        assertThat(list.toArray(), is(new Integer[] {1, 6, 8, 2, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.addAll(2, new RArrayList<>(6, 8));
        assertThat(list.toArray(), is(new Integer[] {1, 2, 6, 8, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.addAll(3, new RArrayList<>(6, 8));
        assertThat(list.toArray(), is(new Integer[] {1, 2, 3, 6, 8}));

        try {
            list = new RArrayList<>(1, 2, 3);
            list.addAll(4, new RArrayList<>(6, 8));
            fail("This should explode");
        } catch (ArrayIndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void clear() throws Exception {
        List<Integer> list = new RArrayList<>();
        list.clear();
        assertThat(list.size(), is(0) );
        list = new RArrayList<>(1, 2, 3);
        list.clear();
        assertThat(list.size(), is(0));
    }

    @Test
    public void get() throws Exception {
        List<Integer> list = new RArrayList<>();
        try {
            list.get(0);
            fail("This should explode");
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }

        try {
            list.get(4);
            fail("This should explode");
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }

        list = new RArrayList<>(1, 2, 3);
        assertThat(list.get(0), is(1));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.get(1), is(2));
        list = new RArrayList<>(1, 2, 3);
        assertThat(list.get(2), is(3));
    }

    @Test
    public void set() throws Exception {
        List<Integer> list = new RArrayList<>();
        try {
            list.set(0, 1);
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }

        list = new RArrayList<>(1, 2, 3);
        list.set(0, 6);
        assertThat(list.toArray(), is(new Integer[]{6, 2, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.set(1, 6);
        assertThat(list.toArray(), is(new Integer[]{1, 6, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.set(2, 6);
        assertThat(list.toArray(), is(new Integer[]{1, 2, 6}));
    }

    @Test
    public void addWithIndex() throws Exception {
        List<Integer> list = new RArrayList<>();
        list.add(0,6);
        assertThat(list.toArray(), is(new Integer[] {6}));

        list = new RArrayList<>(1, 2, 3);
        list.add(0, 6);
        assertThat(list.toArray(), is(new Integer[] {6, 1, 2, 3 }));

        list = new RArrayList<>(1, 2, 3);
        list.add(1, 6);
        assertThat(list.toArray(), is(new Integer[] {1, 6, 2, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.add(2, 6);
        assertThat(list.toArray(), is(new Integer[] {1, 2, 6, 3}));

        list = new RArrayList<>(1, 2, 3);
        list.add(3, 6);
        assertThat(list.toArray(), is(new Integer[] {1, 2, 3, 6}));

        try {
            list = new RArrayList<>(1, 2, 3);
            list.add(4, 6);
            fail("This should explode");
        } catch (ArrayIndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void remove1() throws Exception {
    }

    @Test
    public void indexOf() throws Exception {
    }

    @Test
    public void lastIndexOf() throws Exception {
    }

    @Test
    public void listIterator() throws Exception {
    }

    @Test
    public void listIterator1() throws Exception {
    }

    @Test
    public void subList() throws Exception {
    }

    @Test
    public void retainAll() throws Exception {
    }

    @Test
    public void removeAll() throws Exception {
    }

    @Test
    public void containsAll() throws Exception {
    }

    @Test
    public void toArray1() throws Exception {
    }

}