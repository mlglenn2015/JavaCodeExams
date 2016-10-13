package prv.mark.test.processtextfile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Created by mlglenn on 10/13/2016.
 */
public final class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String COMMA = ",";
    public static final String LEFT_PAREN = "(";
    public static final String RIGHT_PAREN = ")";


    /**
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    /**
     *
     * @param inStr
     * @param delim
     * @return
     */
    public static String getFirstTokenFromString(final String inStr, final String delim) {
        if (isEmpty(inStr) || isEmpty(delim)) {
            return EMPTY;
        }

        try {
            return inStr.split(delim)[0];
        } catch (Exception e) {
            LOGGER.debug("StringUtils.getFirstTokenFromString(): Exception caught {}", e);
            return EMPTY;
        }
    }

    /**
     *
     * @param inStr
     * @param delim
     * @return
     */
    public static String getTokenNumberFromString(final String inStr, final String delim, final int tokenNumber) {
        if (isEmpty(inStr) || isEmpty(delim)) {
            return EMPTY;
        }
        String returnStr = EMPTY;

        try {
            List<String> tokenArray = returnTokensAsArrayOfString(inStr, delim);
            if (tokenArray == null || tokenArray.size() == 0) {
                return EMPTY;
            }

            //Testing
            for (String token : tokenArray) {
                LOGGER.debug("TOKEN: {}", token);
            }

            returnStr = tokenArray.get(tokenNumber - 1); //returning the token

        } catch (Exception e) {
            LOGGER.debug("StringUtils.getTokenNumberFromString(): Exception caught {}", e);
        }

        LOGGER.debug("Returning: {}", returnStr);
        return returnStr;
    }

    /**
     *
     * @param inStr
     * @param delim
     * @return
     */
    public static String getLastTokenFromString(final String inStr, final String delim) {
        if (isEmpty(inStr) || isEmpty(delim)) {
            return EMPTY;
        }
        String returnStr = EMPTY;

        StringTokenizer st = new StringTokenizer(inStr, delim);
        if (st.hasMoreTokens()) {
            LOGGER.debug("StringUtils.getLastTokenFromString(): # TOKENS: {}", st.countTokens());
            //LOGGER.debug("StringUtils.getLastTokenFromString(): nextToken {}", st.nextToken());

            try {
                returnStr = inStr.split(delim)[st.countTokens()-1];
            } catch (Exception e) {
                LOGGER.debug("StringUtils.getLastTokenFromString(): Exception caught {}", e);
                return EMPTY;
            }
        }
        LOGGER.debug("Returning: {}", returnStr);
        return returnStr;
    }

    /**
     *
     * @param input
     * @param delim
     * @return
     */
    public static List<String> returnTokensAsArrayOfString(final String input, final String delim) {
        if (isEmpty(input)) {
            return new ArrayList<>();
        }
        List<String> returnList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(input, delim);
        while (st.hasMoreTokens()) {
            returnList.add(st.nextToken());
        }
        return returnList;
    }

    /**
     *
     * @param str
     * @return
     */
    public static String safeString(final String str) {
        return Optional.ofNullable(str).orElse(EMPTY);
    }


}
