/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {
    String url = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";

    Map<String, List<String>> anagramsByWord = new BufferedReader(new InputStreamReader(new URL(url).openStream()))
            .lines()
            .collect(Collectors.groupingBy(Main::sortChars));

    int maxCount = anagramsByWord.values()
            .stream()
            .mapToInt(List::size)
            .max()
            .orElse(0);

    anagramsByWord.entrySet()
            .stream()
            .filter(entry -> entry.getValue().size() == maxCount)
            .forEach(entry ->
                    System.out.println(
                            entry.getKey() + " " + String.join(" ", entry.getValue())
                    )
            );
  }

  private static String sortChars(String str) {
    return str.chars()
            .sorted()
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining());
  }
}
