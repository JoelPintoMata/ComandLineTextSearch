package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class IndexElemTest {

    private IndexElem indexElem;

    @BeforeEach
    void setUp() {
        this.indexElem = new IndexElem("someFilename", "someWord");
    }

    @Test
    void getSourceList() {
    }

    @Test
    void getWord() {
        Assertions.assertEquals(this.indexElem.getTerm(), "someWord");
    }

    @Test
    void addSource() {
        this.indexElem.addFilename("someOtherFilename");
        Assertions.assertArrayEquals(this.indexElem.getResourceList().toArray(), new String[]{"someOtherFilename", "someFilename"});
    }
}