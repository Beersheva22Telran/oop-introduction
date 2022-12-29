package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest {
	Integer [] numbers = {10, 100, -5, 134, 280, 120, 15};
	Integer ar[] = new Integer[numbers.length + 100];
	List<Integer> list;
	Integer [] empty = {};
	@BeforeEach
	void setUp() throws Exception {
		list = new ArrayList<>(2);
		for(Integer num: numbers) {
			list.add(num);
		}
	}

	@Test
	void testAdd() {
		
		
	}

	@Test
	void testRemove() {
		Integer [] expected = {10, 100, -5,  280, 120, 15};
		assertTrue(list.remove((Integer)134));
		assertArrayEquals(expected, list.toArray(empty));
		assertFalse(list.remove((Integer)134));
	}

	@Test
	void testRemoveIf() {
		Integer []expected = {-5, 15};
		assertTrue(list.removeIf(n -> n % 2 == 0));
		assertArrayEquals(expected, list.toArray(empty));
		assertFalse(list.removeIf(n -> n % 2 == 0));
		assertTrue(list.removeIf(n -> true));
		assertTrue(list.isEmpty());
		
	}

	@Test
	void testIsEmpty() {
		assertFalse(list.isEmpty());
		list.removeIf(n -> true);
		assertTrue(list.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, list.size());
	}

	@Test
	void testContains() {
		assertTrue(list.contains(numbers[0]));
		assertFalse(list.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		
		Arrays.fill(ar, 10);
		list.toArray(ar);
		for(int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for(int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
		
	}

	@Test
	void testAddInt() {
		Integer [] expected1 = {10, 100, -5, 100, 134, 280, 120, 15};
		Integer [] expected2 = {8, 10, 100, -5, 100, 134, 280, 120, 15};
		Integer [] expected3 = {8, 10, 100, -5, 100, 134, 280, 120, 15, 200};
		try {
			list.add(1000, 1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
		list.add(3, 100);
		assertArrayEquals(expected1, list.toArray(empty));
		list.add(0, 8);
		assertArrayEquals(expected2, list.toArray(empty));
		list.add(list.size(), 200);
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testRemoveInt() {
		Integer [] expected1 = {10, 100, -5, 280, 120, 15};
		Integer [] expected2 = { 100, -5,  280, 120, 15};
		Integer [] expected3 = { 100, -5,  280, 120};
		try {
			list.remove(1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
		assertEquals(134,list.remove(3));
		assertArrayEquals(expected1, list.toArray(empty));
		assertEquals(10, list.remove(0));
		assertArrayEquals(expected2, list.toArray(empty));
		assertEquals(15,list.remove(list.size() - 1));
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testIndexOf() {
		for(int i = 0; i < numbers.length; i++) {
			assertEquals(i, list.indexOf(numbers[i]));
		}
	}

	@Test
	void testLastIndexOf() {
		list.add(3, 134);
		assertEquals(3, list.indexOf(134));
		assertEquals(4, list.lastIndexOf(134));
		
	}

	@Test
	void testGet() {
		try {
			list.get(1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
		assertEquals(10, list.get(0));
	}

	@Test
	void testSet() {
		list.set(0, 1000);
		assertEquals(1000, list.get(0));
	}

}
