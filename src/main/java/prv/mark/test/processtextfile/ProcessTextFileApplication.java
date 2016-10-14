package prv.mark.test.processtextfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Process the persons.txt file
 *
 * @author mlglenn
 */
public class ProcessTextFileApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTextFileApplication.class);
    private static final String inputFileName = "persons.txt";  //TODO move to a config table or file
    private static final String outputFileName = "C:\\GitHub_Projects\\JavaCodeExams\\src\\main\\resources\\json_persons.txt";

    /**
     *
     * @param args {@link String[]}
     */
    public static void main(String[] args) {

        LOGGER.debug("*** ProcessTextFileApplication.main() executing... ***");

        PersonFileProcessor fileProcessor = new PersonFileProcessor(inputFileName, outputFileName);
        fileProcessor.processFile();

        LOGGER.debug("*** ProcessTextFileApplication.main() finished. ***");
    }


}
