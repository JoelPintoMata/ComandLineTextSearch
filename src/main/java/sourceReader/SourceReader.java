package sourceReader;


import index.Index;

/**
 * Data source reader interface
 */
public interface SourceReader {

    /**
     * Starts this source read process
     */
    void read();

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
