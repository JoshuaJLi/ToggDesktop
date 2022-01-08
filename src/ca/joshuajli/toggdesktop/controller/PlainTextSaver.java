package ca.joshuajli.toggdesktop.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlainTextSaver {

    private static final String SAVE_FILE = "FavEntries.txt";

    public static String load() throws IOException {
        return Files.readString(Paths.get(SAVE_FILE));
    }

    public static void save(String favString) {
        try {
            Writer faveEntryFile = Files.newBufferedWriter(Paths.get("./" + SAVE_FILE));
            faveEntryFile.write(favString);
            faveEntryFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
