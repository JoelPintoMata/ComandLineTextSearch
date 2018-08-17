package model;


/**
 * <Key, Value> pair class
 */
public class Pair {

    private final String key;
    private final String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return this.value;
    }
}
