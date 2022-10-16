import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {
    private final int VALID_CAPACITY = (LRUCache.MAX_CAPACITY + LRUCache.MIN_CAPACITY) / 2;

    @Test
    public void testIllegalCapacity() {
        int negativeCapacity = -10;
        invalidCapacityAssert(negativeCapacity);

        int zeroCapacity = 0;
        invalidCapacityAssert(zeroCapacity);

        int lessThenMinCapacity = LRUCache.MIN_CAPACITY - 1;
        invalidCapacityAssert(lessThenMinCapacity);

        int moreThenMaxCapacity = LRUCache.MAX_CAPACITY + 1;
        invalidCapacityAssert(moreThenMaxCapacity);
    }

    private void invalidCapacityAssert(int invalidCapacity) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LRUCache(invalidCapacity));

        String expectedMessage = "Illegal capacity: ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSimplePut() {
        LRUCache cache = new LRUCache(VALID_CAPACITY);

        for (int i = 0; i < VALID_CAPACITY; i++) {
            cache.put(i, i);
        }

        for (int i = 0; i < VALID_CAPACITY; i++) {
            assertEquals(cache.get(i), i);
        }
    }

    @Test
    public void testGetNonExistentElements() {
        LRUCache cache = new LRUCache(VALID_CAPACITY);

        // Empty cache
        for (int i = 0; i < VALID_CAPACITY; i++) {
            assertEquals(cache.get(i), -1);
        }

        // Partial filled
        for (int i = 0; i < VALID_CAPACITY / 2; i++) {
            cache.put(i, i);
        }

        for (int i = VALID_CAPACITY / 2; i < VALID_CAPACITY; i++) {
            assertEquals(cache.get(i), -1);
        }

        // Full cache
        for (int i = 0; i < VALID_CAPACITY; i++) {
            cache.put(i, i);
        }

        for (int i = 0; i < VALID_CAPACITY; i++) {
            assertEquals(cache.get(VALID_CAPACITY + i), -1);
        }
    }

    @Test
    public void testPut() {
        LRUCache cache = new LRUCache(VALID_CAPACITY);

        for (int i = 0; i < VALID_CAPACITY; i++) {
            cache.put(i, -1);
        }

        for (int i = 0; i < VALID_CAPACITY; i++) {
            cache.put(i, i);
        }

        for (int i = 0; i < VALID_CAPACITY; i++) {
            assertEquals(cache.get(i), i);
        }
    }


    @Test
    public void testComplexity() {
        int cntRepeat = 10;
        long allTime = 0;
        for (int i = 0; i < cntRepeat; i++) {
            LRUCache cache = new LRUCache(VALID_CAPACITY);
            allTime += calculateTime(cache, 1_000_000);
        }
        double avgTime = allTime / (cntRepeat * 1.);
        assertTrue(avgTime < 100);
    }

    private long calculateTime(LRUCache cache, int cntAction) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < cntAction; i++) {
            cache.put(i, i);
        }

        for (int i = 0; i < cntAction; i++) {
            cache.get(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
