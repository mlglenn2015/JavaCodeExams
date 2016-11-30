package prv.mark.test.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the {@link BooleanUtils} class.
 *
 * @author mlglenn on 11/29/2016.
 */
public class BooleanUtilsTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanUtilsTests.class);


    @Test
    public void testIsCapAlpha() {
        boolean result = BooleanUtils.isCapAlpha("A");
        LOGGER.debug("Is A a capital letter? {}", result);
        assertTrue(result);
    }

    @Test
    public void testNotIsCapAlpha() {
        assertFalse(BooleanUtils.isCapAlpha("a"));
    }
}
