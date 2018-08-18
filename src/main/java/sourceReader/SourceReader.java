package sourceReader;


import index.Indexer;

/**
 * Data source reader interface
 */
public interface SourceReader {

    /**
     * Starts this source read process
     */
    void read();

    /**
     * Sets this source indexer
     * @param indexer an indexer
     */
    void setIndexer(Indexer indexer);

    /**
     * Sets this source location
     * @param location an location
     */
    void setSourceLocation(String location);
}
