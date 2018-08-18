package model;


/**
 * <Key, Value> pair class
 */
public class Pair<String, T> {

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
