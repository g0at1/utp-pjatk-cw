/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    String url = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";
    Anagrams anagrams = new Anagrams(url);

    List<List<String>> sortedAnagrams = anagrams.getSortedByCount();
    int maxCount = sortedAnagrams.get(0).size();

    sortedAnagrams.stream()
            .filter(wordList -> wordList.size() == maxCount)
            .forEach(wordList -> {
              System.out.print(wordList.get(0) + " ");
              System.out.println(wordList.stream().skip(1).collect(Collectors.joining(" ")));
            });
  }
}
