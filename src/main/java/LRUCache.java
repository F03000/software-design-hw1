import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class LRUCache<K, V> {
    private final Map<K, V> keyValueMap;
    private final List<K> orderedKeyList;
    private final int capacity;

    public LRUCache(int capacity) {
        assert (capacity > 0);
        keyValueMap = new HashMap<>(capacity);
        orderedKeyList = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        assertNotNull(key);
        assertNotNull(value);

        keyValueMap.put(key, value);
        if (!orderedKeyList.contains(key) && orderedKeyList.size() == capacity) {
            K removedKey = orderedKeyList.remove(orderedKeyList.size() - 1);
            keyValueMap.remove(removedKey);
        }

        putToFirstWithoutCopies(key);

        assert (orderedKeyList.get(0) == key);
        assert (keyValueMap.containsKey(key));
        assert (keyValueMap.containsValue(value));
    }

    public V get(K key) {
        assertNotNull(key);
        V value = keyValueMap.get(key);
        assertNotNull(value);
        putToFirstWithoutCopies(key);
        return value;
    }

    public int size() {
        assert (orderedKeyList.size() == keyValueMap.size());
        return orderedKeyList.size();
    }

    public boolean contains(K key, V value) {
        assertNotNull(key);
        assertNotNull(value);
        return keyValueMap.get(key) == value;
    }

    public int getCapacity() {
        return capacity;
    }

    private void putToFirstWithoutCopies(K key) {
        orderedKeyList.remove(key);
        orderedKeyList.add(0, key);
    }

    private static void assertNotNull(Object object) {
        assert object != null;
    }
}
