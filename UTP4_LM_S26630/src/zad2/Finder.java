/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Finder {
    private String filePath;

    public Finder(String filePath) {
        this.filePath = filePath;
    }

    public int getIfCount() throws IOException {
        int counter = 0;
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            if (line.contains("if") && !line.trim().startsWith("//")) {
                counter++;
            }
        }

        br.close();

        return counter;
    }

    public int getStringCount(String searchString) throws IOException {
        int counter = 0;
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            counter += countOccurrences(line, searchString);
        }

        br.close();

        return counter;
    }

    private int countOccurrences(String line, String str) {
        int counter = 0;
        int index = 0;

        while ((index = line.indexOf(str, index)) != -1) {
            counter++;
            index += str.length();
        }

        return counter;
    }
}
