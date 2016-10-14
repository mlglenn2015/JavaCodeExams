package prv.mark.test.processtextfile;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonFileProcessorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonFileProcessorTests.class);

    @Before
    public void setUp() {
        LOGGER.debug("PersonFileProcessorTests.setUp()");
    }

    @After
    public void tearDown() {
        LOGGER.debug("PersonFileProcessorTests.tearDown()");
    }

    @Test
    public void testPersonFileProcessorNullInputFile() {
        String outFile = "dummy_output_file.txt";
        PersonFileProcessor personFileProcessor = new PersonFileProcessor(null, outFile);
        personFileProcessor.processFile();
    }

    @Test
    public void testPersonFileProcessorNullOutputFile() {
        String inFile = "test_persons.txt";
        PersonFileProcessor personFileProcessor = new PersonFileProcessor(inFile, null);
        personFileProcessor.processFile();
    }

    @Test
    public void testPersonFileProcessor() {
        String inFile = "test_persons.txt";
        String outFile = "C:\\temp\\dummy_output_file.txt";
        PersonFileProcessor personFileProcessor = new PersonFileProcessor(inFile, outFile);
        personFileProcessor.processFile();
    }
}