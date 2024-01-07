import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomMap<K, V> {
    private List<Pair<K, V>> list;

    public CustomMap() {
        list = new ArrayList<>();
    }
    public List<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (Pair<K, V> pair : list) {
            keys.add(pair.getKey());
        }
        return keys;
    }

    public void put(K key, V value) {
        for (Pair<K, V> pair : list) {
            if (pair.getKey().equals(key)) {
                pair.setValue(value);
                return;
            }
        }
        list.add(new Pair<>(key, value));
    }

    public V get(K key) {
        for (Pair<K, V> pair : list) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        list.removeIf(pair -> pair.getKey().equals(key));
    }

    public boolean containsKey(K key) {
        for (Pair<K, V> pair : list) {
            if (pair.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return list.size();
    }
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (Pair<K, V> pair : list) {
            values.add(pair.getValue());
        }
        return values;
    }

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

