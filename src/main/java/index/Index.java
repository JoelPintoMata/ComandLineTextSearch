package index;

import model.IndexElem;

import java.util.TreeMap;

public class Index {

    private static TreeMap<String, IndexElem> data = new TreeMap<>( );
    private final SourceReader sourceReader;

    public Index(SourceReader sourceReader) {
        this.sourceReader = sourceReader;
        this.sourceReader.setIndex(this);
    }

    public static void index(String term, String source) {
        // convert to lower case.
        term = term.toLowerCase();

        if(term.length() == 0)
            return;

        IndexElem indexElem = new IndexElem(source, term);

        var oldVar = data.get(term);
        if(oldVar == null) {
            data.put(term, indexElem);
        } else {
            oldVar.addFilename(source);
        }
    }

    public TreeMap<String, IndexElem> get() {
        return this.data;
    }
}
