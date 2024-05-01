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
        BufferedReader reader = new BufferedReader(fr);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("if") && !isInCommentOrString(line)) {
                counter++;
            }
        }
        reader.close();

        return counter;
    }

    public int getStringCount(String str) throws IOException {
        int counter = 0;
        FileReader fr = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(str) && !isInCommentOrString(line)) {
                counter++;
            }
        }
        reader.close();

        return counter;
    }

    public boolean isInComment(String line) {
        return line.contains("//");
    }

    public boolean isInString(String line) {
        return line.contains("\"");
    }

    public boolean isInCommentOrString(String line) {
        return isInComment(line) || isInString(line);
    }

}
