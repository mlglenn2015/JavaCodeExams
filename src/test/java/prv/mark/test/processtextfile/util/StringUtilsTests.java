package prv.mark.test.processtextfile.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mlglenn on 10/13/2016.
 */
public class StringUtilsTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilsTests.class);

    @Before
    public void setUp() {
        LOGGER.debug("setUp()");
    }

    @After
    public void tearDown() {
        LOGGER.debug("tearDown()");
    }


    @Test
    public void testIsEmpty() {
        String emptyStr = "";
        assertTrue(StringUtils.isEmpty(emptyStr));
    }

    @Test
    public void testGetFirstTokenFromString() {
        String testStr = "My Test String";
        String testToken = "My";
        String returnStr = StringUtils.getFirstTokenFromString(testStr, " ");
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetFirstTokenFromStringNull() {
        String testStr = null;
        String testToken = "";
        String returnStr = StringUtils.getFirstTokenFromString(testStr, " ");
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetSecondTokenFromString() {
        String testStr = "My(Test(String";
        String testToken = "Test";
        String returnStr = StringUtils.getTokenNumberFromString(testStr, "(", 2);
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetFifthTokenFromString() {
        String testStr = "My Test String Has More Tokens";
        String testToken = "More";
        String returnStr = StringUtils.getTokenNumberFromString(testStr, " ", 5);
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetFifthTokenFromStringException() {
        String testStr = "My Test String";
        String testToken = "";
        String returnStr = StringUtils.getTokenNumberFromString(testStr, "(", 5);
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetLastTokenFromString() {
        String testStr = "My Test String";
        String testToken = "String";
        String returnStr = StringUtils.getLastTokenFromString(testStr, " ");
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testGetLastTokenFromStringNull() {
        String testStr = null;
        String testToken = "";
        String returnStr = StringUtils.getFirstTokenFromString(testStr, " ");
        assertEquals(returnStr, testToken);
    }

    @Test
    public void testReturnTokensAsArray() {
        String testStr = "My Test String Has Six Tokens";
        String testToken = "More";
        List<String> tokenArray = StringUtils.returnTokensAsArrayOfString(testStr, " ");
        assertNotNull(tokenArray);
        assertTrue(tokenArray.size() > 0);
    }

    @Test
    public void testSafeString() {
        String safeString = "Safe String";
        String nullString = null;
        String empty = "";
        assertEquals(StringUtils.safeString(safeString), safeString);
        assertEquals(StringUtils.safeString(nullString), empty);
    }

}
