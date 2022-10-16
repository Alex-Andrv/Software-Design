import java.util.HashMap;
import java.util.Map;

class LRUCache {
    /*
        Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

        Implement the LRUCache class:

        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
        int get(int key) Return the value of the key if the key exists, otherwise return -1.
        void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
        The functions get and put must each run in O(1) average time complexity.
     */

    public static final int MIN_CAPACITY = 1;
    public static final int MAX_CAPACITY = 1000;
    private final Entry head;
    private final Entry tail;

    private class Entry {
        private Entry before;
        private Entry after;
        private int value;
        private final int key;

        private Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        private int getValue() {
            return value;
        }

        private int getKey() {
            return key;
        }

        private void addAfter(Entry entry) {
            assert entry != null;
            assert this == head;
            Entry rightNeighbor = after;

            if (after == null) {
                assert entry == tail;
                after = entry;
                entry.before = this;
                assert entry.after == null;
            } else {
                entry.before = this;
                entry.after = after;
                after.before = entry;
                after = entry;
                assert rightNeighbor.before == entry;
            }

            assert this.after == entry;
            assert (entry.before == this) && (entry.after ==  rightNeighbor);
        }

        private void drop() {
            assert before != null;
            assert after != null;
            assert this != tail;
            assert this != head;
            Entry leftNeighbor = before;
            Entry rightNeighbor = after;

            before.after = after;
            after.before = before;

            assert rightNeighbor.before == leftNeighbor;
            assert leftNeighbor.after == rightNeighbor;
        }

        public void updateValue(int value) {
            this.value = value;

            assert this.value == value;
        }
    }

    private final int capacity;
    private final Map<Integer, Entry> cache;

    public LRUCache(int capacity) {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Illegal capacity: " +
                    capacity);
        }

        assert capacity > 0;

        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Entry(-1, -1);
        tail = new Entry(-1, -1);
        head.addAfter(tail);

        assert head != tail;
        assert head != null;
        assert tail != null;
        assert head.after == tail;
        assert tail.before == head;
        assert cache != null;
        assert cache.size() == 0;
        assert this.capacity == capacity;
    }

    public int get(int key) {
        Entry entry = cache.get(key);
        if (entry == null) {
            return -1;
        } else {
            upPriority(entry);
            assert head.after == entry;
            return entry.getValue();
        }
    }

    private void upPriority(Entry entry) {
        assert cache.get(entry.getKey()) == entry;
        assert entry.after != null && entry.before != null;

        entry.drop();
        head.addAfter(entry);

        assert head.after == entry;
    }

    public void put(int key, int value) {
        Entry entry = cache.get(key);
        if (entry == null) {
            entry = new Entry(key, value);
            if (this.capacity <= cache.size()) {
                Entry lowPriorityEntry = tail.before;
                assert head != lowPriorityEntry;
                cache.remove(lowPriorityEntry.getKey());
                lowPriorityEntry.drop();
            }
            head.addAfter(entry);
            cache.put(key, entry);
        } else {
            entry.updateValue(value);
            upPriority(entry);
        }

        assert cache.get(key).getValue() == value;
        assert head.after == entry;
    }
}