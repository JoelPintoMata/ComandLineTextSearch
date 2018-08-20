package utils;

import java.text.Normalizer;

/**
 * Collection of file utilities methods
 */
public class StringUtils {

    /**
     * Sanitizes a string input by converting accented to non-accented characters
     *
     * @param string the string to sanitize
     * @return a list of text file names from the project root
     */
    public static String removeAccents(String string) {
        return string == null ? null :
                Normalizer.normalize(string, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Sanitizes a string input
     * Converts accented characters
     * Converts to lower case
     * Trims white spaces
     *
     * @param string the string to sanitize
     * @return
     */
    public static String sanitize(String string) {
//        convert to lower case
//        trims white spaces
        string = string
                .toLowerCase()
                .trim();

        string = StringUtils.removeAccents(string);

        return string;
    }
}
