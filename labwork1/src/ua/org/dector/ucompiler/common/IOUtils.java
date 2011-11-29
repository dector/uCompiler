package ua.org.dector.ucompiler.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Some common method, used as usefull utils
 *
 * @author dector
 */
public class IOUtils {
    /**
     * Returns list of lines, which selected text file contains.
     *
     * @param filename full or relative path to file to load
     * @return list of lines in file
     */
    public static List<String> loadFile(String filename) {
        List<String> queriesList = new ArrayList<String>();

        Scanner in = null;
        try {
            in = new Scanner(new BufferedReader(new FileReader(filename)));

            String nextQueryLine;
            while (in.hasNext()) {
                nextQueryLine = in.nextLine();
                queriesList.add(nextQueryLine);
            }
        } catch (FileNotFoundException e) {
            //todo: I'm so trivial exceptions. Please, process me!
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return queriesList;
    }
}
