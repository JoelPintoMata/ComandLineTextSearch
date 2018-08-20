package rank;

import model.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class RankTFxIDFImplTest {

    Rank rank = new RankTFxIDFImpl();

    @BeforeEach
    void setUp() {
        this.rank.setNumberOfSources(5);
        this.rank.setQueryTermsArray(new String[]{"one", "two", "three", "four", "five"});
        Map<Pair<String, String>, Integer> map = new HashMap<>();
        Pair pair = new Pair("one", "one.txt");
        map.put(pair, 1);
        pair = new Pair("two", "one.txt");
        map.put(pair, 1);
        pair = new Pair("three", "one.txt");
        map.put(pair, 1);

        pair = new Pair("one", "two.txt");
        map.put(pair, 1);
        pair = new Pair("two", "two.txt");
        map.put(pair, 2);
        pair = new Pair("three", "two.txt");
        map.put(pair, 2);

        pair = new Pair("one", "three.txt");
        map.put(pair, 1);
        pair = new Pair("two", "three.txt");
        map.put(pair, 2);
        pair = new Pair("three", "three.txt");
        map.put(pair, 3);

        pair = new Pair("one", "four.txt");
        map.put(pair, 1);
        pair = new Pair("two", "four.txt");
        map.put(pair, 1);

        pair = new Pair("one", "five.txt");
        map.put(pair, 1);

        this.rank.setTF(map);
    }

    @Test
    void rankTest() {

        Comparator<? super Pair> comparator = this.rank.getComparator();

        List<Pair> list = new ArrayList<>();
        list.add(new Pair("one.txt", (long)100));
        list.add(new Pair("four.txt", (long)66));
        list.add(new Pair("two.txt", (long)100));
        list.add(new Pair("five.txt", (long)33));
        list.add(new Pair("three.txt", (long)100));

        list.sort(comparator);
        Assertions.assertEquals(list.get(0), new Pair("three.txt", (long) 100));
        Assertions.assertEquals(list.get(1), new Pair("two.txt", (long) 100));
        Assertions.assertEquals(list.get(2), new Pair("one.txt", (long) 100));
        Assertions.assertEquals(list.get(3), new Pair("four.txt", (long) 66));
        Assertions.assertEquals(list.get(4), new Pair("five.txt", (long) 33));
    }
}