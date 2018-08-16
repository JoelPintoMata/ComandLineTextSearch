package index;


/**
 *
 */
public interface SourceReader {

    /**
     * Starts this source index process
     */
    void index();

    /**
     * Sets this source index
     * @param index an index
     */
    void setIndex(Index index);

    /**
     * Sets this source location
     * @param location an location
     */
    void setSourceLocation(String location);
}
