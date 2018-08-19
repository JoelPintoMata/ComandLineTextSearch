package index;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ranker.Ranker;
import ranker.RankTFxIDFImpl;

import java.util.List;


class IndexerTest {

    private Indexer indexer;

    @BeforeEach
    void setUp() {
        Ranker ranker = new RankTFxIDFImpl();
        this.indexer = new Indexer();
        this.indexer.setRank(ranker);
    }

    @Test
    void index() {
//        test add insertion
        this.indexer.add("someTerm", "someFile");
        Assertions.assertEquals(this.indexer.search("someOtherTerm").size(), 1);
    }

    @Test
    void search() {
//        test no match
        this.indexer.add("someTerm", "someFile");
        Assertions.assertEquals(this.indexer.search("someOtherTerm").size(), 0);

//        one term search
        List<String> list = this.indexer.search("someTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 100%");

//        two term search
        list = this.indexer.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 50%");

//        add the second term, test 100 accuracy and casing independent
        this.indexer.add("SOMEotherTERM", "someFile");
        list = this.indexer.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 100%");

//        add a second file, test accuracy and casing independent
        this.indexer.add("SOMETERM", "someOtherFile");
        list = this.indexer.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(list.get(0), "someFile: 100%");
        Assertions.assertEquals(list.get(1), "someOtherFile: 50%");
    }
}