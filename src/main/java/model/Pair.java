package model;


import java.util.Objects;

/**
 * <Key, Value> pair class
 */
public class Pair<String, T> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;

        return key.equals(pair.key) &&
                Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    private final String key;
    private final T value;

    public Pair(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return this.value;
    }
}
