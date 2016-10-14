package prv.mark.test.processtextfile;

import java.io.File;

/**
 * Super class to the file processors.
 *
 * Created by mlglenn on 10/14/2016.
 */
public abstract class AbstractFileProcessor {

    private String inputFileName;
    private String outputFileName;

    public abstract void processFile();


    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String fileName) {
        this.inputFileName = fileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    protected File getFile(final String fileName) {

        if (! fileName.isEmpty()) {
            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            return new File(classLoader.getResource(fileName).getFile());
        }
        return null;
    }

}
