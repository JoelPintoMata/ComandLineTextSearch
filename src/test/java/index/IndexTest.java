package index;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rank.Rank;
import rank.RankTFxIDFImpl;

import java.util.List;


class IndexTest {

    private Index index;

    @BeforeEach
    void setUp() {
        Rank rank = new RankTFxIDFImpl();
        this.index = new Index();
        this.index.setRank(rank);
    }

    @Test
    void index() {
//        test add insertion
        this.index.add("someTerm", "someFile");
        Assertions.assertEquals(this.index.search("someOtherTerm").size(), 1);
    }

    @Test
    void search() {
//        test no match
        this.index.add("someTerm", "someFile");
        Assertions.assertEquals(this.index.search("someOtherTerm").size(), 0);

//        one term search
        List<String> list = this.index.search("someTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 100%");

//        two term search
        list = this.index.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 50%");

//        add the second term, test 100 accuracy and casing independent
        this.index.add("SOMEotherTERM", "someFile");
        list = this.index.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), "someFile: 100%");

//        add a second file, test accuracy and casing independent
        this.index.add("SOMETERM", "someOtherFile");
        list = this.index.search("someTerm someOtherTerm");
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(list.get(0), "someFile: 100%");
        Assertions.assertEquals(list.get(1), "someOtherFile: 50%");
    }
}