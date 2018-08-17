package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void getFiles() {
        List<String> fileList = FileUtils.getFiles("./src/test/resources", ".txt");
        Assertions.assertEquals(fileList.size(), 7);

        fileList = FileUtils.getFiles("./src/test/resources", ".xxx");
        Assertions.assertEquals(fileList.size(), 0);
    }
}