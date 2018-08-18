package sourceReader;

public enum SourceTypeEnum {
    TEXT(".txt");

    private final String value;

    SourceTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
