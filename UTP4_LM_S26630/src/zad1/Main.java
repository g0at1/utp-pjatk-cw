/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {
    Map<String, List<String>> anagramsByWord = Files.lines(Paths.get("unixdict.txt"))
            .collect(Collectors.groupingBy(Main::sortChars));

    int maxAnagramsCount = anagramsByWord.values().stream()
            .mapToInt(List::size)
            .max()
            .orElse(0);

    anagramsByWord.entrySet().stream()
            .filter(entry -> entry.getValue().size() == maxAnagramsCount)
            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().stream().collect(Collectors.joining(" "))));
  }

  private static String sortChars(String word) {
    return word.chars().sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
  }
}
