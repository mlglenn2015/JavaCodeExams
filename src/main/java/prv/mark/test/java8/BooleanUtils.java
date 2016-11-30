package prv.mark.test.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Utility methods that have return type {@link Boolean}.
 *
 * @author mlglenn on 11/29/2016.
 */
public class BooleanUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanUtils.class);

    /**
     * Determines whether aan input char is uppercase.
     *
     * @param inputVal The input string
     * @return {@Boolean}
     */
    public static Boolean isCapAlpha(final String inputVal) {
        return Pattern.matches("[A-Z]", inputVal);
    }

    /**
     * Determines if an input {@link String} matches the associated pattern argument.
     * @param patternStr The input pattern
     * @param matchStr The input string to compare to the pattern
     * @return boolean
     */
    public static boolean testRegEx(String patternStr, String matchStr) {
        boolean result = false;
        try {
            if (Pattern.matches(patternStr, matchStr))
                result = true;
        } catch (Exception e) {
            LOGGER.debug("Exception caught: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid argument.");
        }
        return result;
    }
}
