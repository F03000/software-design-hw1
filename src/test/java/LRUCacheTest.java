import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void basicTest() {
        LRUCache<Integer, String> lruCache = new LRUCache<>(10);

        assertEquals(10, lruCache.getCapacity());
        assertEquals(0, lruCache.size());

        lruCache.put(1, "First");
        assertEquals(10, lruCache.getCapacity());
        assertEquals(1, lruCache.size());
        assertTrue(lruCache.contains(1, "First"));
        assertEquals("First", lruCache.get(1));
    }

    @Test
    void oversizeTest() {
        LRUCache<Integer, String> lruCache = new LRUCache<>(2);
        lruCache.put(1, "First");
        lruCache.put(2, "Second");
        lruCache.put(3, "Third");

        assertTrue(lruCache.contains(3, "Third"));
        assertTrue(lruCache.contains(2, "Second"));
        assertFalse(lruCache.contains(1, "First"));
        assertEquals("Second", lruCache.get(2));

        lruCache.put(4, "Fourth");
        assertTrue(lruCache.contains(4, "Fourth"));
        assertFalse(lruCache.contains(3, "Third"));
        assertTrue(lruCache.contains(2, "Second"));
    }

    @Test
    void repeatedKeyTest() {
        LRUCache<Integer, String> lruCache = new LRUCache<>(10);
        lruCache.put(1, "First");
        lruCache.put(1, "Second");
        lruCache.put(1, "Third");

        assertTrue(lruCache.contains(1, "Third"));
        assertEquals("Third", lruCache.get(1));
        assertEquals(1, lruCache.size());

    }


    @Test
    void wrongBehaviourTest() {
        assertThrows(AssertionError.class, () -> new LRUCache<Integer, String>(-1));
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        assertThrows(AssertionError.class, () -> lruCache.get(null));
        assertThrows(AssertionError.class, () -> lruCache.get(1));
        assertThrows(AssertionError.class, () -> lruCache.put(1, null));
        assertThrows(AssertionError.class, () -> lruCache.put(null, "First"));
        assertThrows(AssertionError.class, () -> lruCache.put(null, null));
    }
}