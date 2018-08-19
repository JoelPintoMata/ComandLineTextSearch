package ranker;

import model.Pair;
import utils.MathUtils;

import java.util.*;


/**
 * Concrete ranking class
 * For more details check: https://www.elephate.com/blog/what-is-tf-idf/
 */
public class RankTFxIDFImpl implements Ranker {

    private Map<Pair<String, String>, Integer> tf;

    private String[] queryTermsArray;
    private int numberOfSources;

    private Comparator<? super Pair> comparator = new Comparator<>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            if(((long)o1.getValue()) > ((long)o2.getValue()))
                return -1;
            else {
                if (((long) o1.getValue()) < ((long) o2.getValue()))
                    return 1;
                else {
                    return rankTFxIDF(o1, o2);
                }
            }
        }
    };

    /**
     * Ranks to pair according to TF*IDF
     * @param pair1
     * @param pair2
     * @return
     */
    private int rankTFxIDF(Pair pair1, Pair pair2) {

        int dfTerms = Arrays.stream(queryTermsArray).mapToInt(x -> {
            Pair pair = new Pair(x, pair1.getKey().toString());
            return tf.getOrDefault(pair, 0);
        }).sum();

        // sum of term frequencies of the query in the source  contained in the first pair
        int tfSource1 = Arrays.stream(queryTermsArray).mapToInt(x -> {
            Pair pair = new Pair(x, pair1.getKey().toString());
            return tf.getOrDefault(pair, 0);
        }).sum();

        double rankTFxIDF1 = tfSource1 * Math.log(MathUtils.div(numberOfSources, dfTerms));

        // sum of term frequencies of the query in the source  contained in the first pair
        int tfSource2 = Arrays.stream(queryTermsArray).mapToInt(x -> {
            Pair pair = new Pair(x, pair2.getKey().toString());
            return tf.getOrDefault(pair, 0);
        }).sum();

        double rankTFxIDF2 = tfSource2 * Math.log(MathUtils.div(numberOfSources, dfTerms));

        if(rankTFxIDF1 <= rankTFxIDF2)
            return -1;
        else
            return 1;
    }

    @Override
    public void setQueryTermsArray(String[] queryTermsArray) {
        this.queryTermsArray = queryTermsArray;
    }

    @Override
    public void setNumberOfSources(int numberOfSources) {
        this.numberOfSources = numberOfSources;
    }

    @Override
    public void setTF(Map<Pair<String, String>, Integer> tf) {
        this.tf = tf;
    }

    @Override
    public Comparator<? super Pair> getComparator() {
        return comparator;
    }
}