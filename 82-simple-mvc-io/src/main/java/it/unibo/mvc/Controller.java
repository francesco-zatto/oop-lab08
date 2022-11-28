package it.unibo.mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static String HOME_FOLDER = System.getProperty("user.home");
    private static String SEPARATOR = System.getProperty("file.separator");
    private File currentFile = new File(HOME_FOLDER + SEPARATOR + "output.txt");

    public File getCurrentFile() {
        return this.currentFile;
    }

    public void setCurrentFile(final File newFile) {
        this.currentFile = Objects.requireNonNull(newFile);
    }

    public String getPath() {
        return this.currentFile.getAbsolutePath();
    }

    public void writeOnCurrentFile(final String content) throws IOException {
        final FileWriter fWriter = new FileWriter(this.currentFile, true);
        fWriter.append(content);
        fWriter.close();
    }

}
