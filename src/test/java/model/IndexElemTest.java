package model;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;


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
        Assertions.assertEquals(this.indexElem.getWord(), "someWord");
    }

    @Test
    void addSource() {
        this.indexElem.addFilename("someOtherFilename");
        Assertions.assertArrayEquals(this.indexElem.getResourceList().toArray(), new String[]{"someOtherFilename", "someFilename"});
    }
}