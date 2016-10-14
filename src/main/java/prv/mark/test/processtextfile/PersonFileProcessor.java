package prv.mark.test.processtextfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prv.mark.test.processtextfile.domain.Person;
import prv.mark.test.processtextfile.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the heart of the file processing.
 *
 * Created by mlglenn on 10/14/2016.
 */
public class PersonFileProcessor extends AbstractFileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonFileProcessor.class);
    private static final String TOK_LBL_NAME = "(name";
    private static final String TOK_LBL_AGE = "(age";
    private static final String TOK_LBL_CITY = "(city";
    private static final String TOK_LBL_FLAGS = "(flags";


    public PersonFileProcessor(final String inputFileName, final String outputFileName) {
        setInputFileName(inputFileName);
        setOutputFileName(outputFileName);
    }

    public void processFile() {
        LOGGER.debug("FileProcessor.processFile()");

        // Step 1: Open the input file and load to a List
        File infile = null;
        try {
            infile = getFile(getInputFileName());
        } catch (Exception e) {
            LOGGER.debug("!!! FileProcessor.processFile(): Unexpected Exception caught: {}", e);
        }

        if (infile != null) {

            try (Scanner scanner = new Scanner(infile)) {

                StringBuilder stringBuilder = new StringBuilder();  //TODO only used for debugging
                List<Person> personsList = new ArrayList<>();
                Person person = new Person();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    LOGGER.debug("Line read: {}", line);

                    stringBuilder.append(line).append("\n");        //TODO only used for debugging

                    if (StringUtils.isEmpty(line)) {
                        LOGGER.debug("*** We have a new Person ***");
                        // Before creating a new Person, save the current Person to an array
                        debugPerson(person);
                        personsList.add(person);
                        person = new Person();
                    } else {
                        // Assign the record data to the Person object
                        setPersonAttribute(line, person);
                    }
                }
                // Scanner exits the while loop and the last Person is not saved to the ArrayList
                if ((person != null) && (personsList != null)) {
                    personsList.add(person);
                }

                scanner.close();  //close input file

                LOGGER.debug(stringBuilder.toString());              //TODO only used for debugging

                // Step 2: Write the output file
                debugPersonsList(personsList);
                writeOutputFile(personsList);

            } catch (IOException ioe) {
                LOGGER.debug("!!! FileProcessor.processFile(): IOException caught: {}", ioe);
            }

        } else {
            LOGGER.debug("!!! FileProcessor.processFile(): Unable to access file {}", getInputFileName());
        }
    }


    private void writeOutputFile(final List<Person> personsList) {

        BufferedWriter bufferedWriter = null;
        try {
            File outFile = new File(getOutputFileName());
            if (!outFile.exists()) {  //make sure file is created if not present
                outFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            // Although our output file looks like a JSON structure,
            // the assignment is to use Java to write the output file, not JSON
            bufferedWriter.write(StringUtils.LEFT_BLOCK);

            int i=0; //used for tracking the last element
            for (Person p : personsList) {
                bufferedWriter.newLine();
                bufferedWriter.write(toFormattedString(p));

                if (!(personsList.size()-1 == i)) { //if we are not at the last array element, append a comma
                    bufferedWriter.write(StringUtils.COMMA);
                }
                i++;
            }

            bufferedWriter.newLine();
            bufferedWriter.write(StringUtils.RIGHT_BLOCK);

            bufferedWriter.flush();


        } catch (IOException ioe) {
            LOGGER.debug("!!! FileProcessor.processFile(): IOException caught: {}", ioe);
        } catch (Exception e) {
            LOGGER.debug("!!! FileProcessor.processFile(): Unexpected Exception caught: {}", e);
        }finally {
            try {
                // Close the writer regardless of what happens...
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                LOGGER.debug("!!! FileProcessor.processFile(): IUnknown Exception caught while closing file {}: {}",
                        getOutputFileName(), e);
            }
        }

    }

    private void setPersonAttribute(final String line, Person person) {

        //Name
        if (StringUtils.getFirstTokenFromString(line, StringUtils.ESC_RIGHT_PAREN).equals(TOK_LBL_NAME)) {
            person.setName(StringUtils.getLastTokenFromString(line, StringUtils.ESC_RIGHT_PAREN));
        }
        //Age
        if (StringUtils.getFirstTokenFromString(line, StringUtils.ESC_RIGHT_PAREN).equals(TOK_LBL_AGE)) {
            person.setAge(Integer.valueOf(StringUtils.getLastTokenFromString(line, StringUtils.ESC_RIGHT_PAREN)));
        }
        //City and State
        if (StringUtils.getFirstTokenFromString(line, StringUtils.ESC_RIGHT_PAREN).equals(TOK_LBL_CITY)) {
            person.setCity(
                    StringUtils.getFirstTokenFromString(
                            StringUtils.getLastTokenFromString(line, StringUtils.ESC_RIGHT_PAREN)
                            , StringUtils.COMMA)
            );
            person.setState(StringUtils.remove(
                    StringUtils.getLastTokenFromString(line, StringUtils.COMMA), StringUtils.BLANK)
            );
            if (person.getState().contains(StringUtils.RIGHT_PAREN)) {
                person.setState(StringUtils.EMPTY);
            }
        }
        //Flags
        if (StringUtils.getFirstTokenFromString(line, StringUtils.ESC_RIGHT_PAREN).equals(TOK_LBL_FLAGS)) {
            person.setFlags(StringUtils.getLastTokenFromString(line, StringUtils.ESC_RIGHT_PAREN));
        }

    }

    /* Specially format the desired output for our assignment */
    private String toFormattedString(final Person person) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("    {\n")
                .append("        \"name\": \"").append(person.getName()).append("\",\n")
                .append("        \"age=\": ").append(person.getAge()).append(",\n")
                .append("        \"city=\": \"").append(person.getCity()).append("\",\n");

        if (!person.getState().isEmpty()) {
            stringBuilder.append("        \"state=\": \"").append(person.getState()).append("\",\n");
        }

        if (!person.getFlags().isEmpty() && person.getFlags().length() == 3) {
            stringBuilder
                    .append("        \"isfemale=\": \"").append(person.getFlags().substring(0,1)).append("\",\n")
                    .append("        \"isemployee=\": \"").append(person.getFlags().substring(1,2)).append("\",\n")
                    .append("        \"iscitizen=\": \"").append(person.getFlags().substring(2,3)).append("\" \n");
        }

        stringBuilder.append("    }");
        return stringBuilder.toString();
    }

    private void debugPerson(final Person person) {
        LOGGER.debug("DEBUGGING Person values: {} ", person.toString());
    }

    private void debugPersonsList(List<Person> personsList) {
        LOGGER.debug("debugPersonsList()");

        personsList.stream().forEach(person -> debugPerson(person));
    }
}
