/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;


/*<--
 *  niezbędne importy
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
    Function<String, List<String>> flines = (file) -> {
      List<String> fileLines = null;

      try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        fileLines = new ArrayList<>();

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
          fileLines.add(currentLine);
        }

        br.close();
        fr.close();
      } catch (IOException e) {
        System.err.println("Error while reading file: " + e.getMessage());
      }
      return fileLines;
    };

    Function<List<String>, String> join = (strings) -> String.join("", strings);

    Function<String, List<Integer>> collectInts = (text) -> {
      List<Integer> ints = new ArrayList<>();
      String[] intsSplit = text.split("\\D+");

      for (String part : intsSplit) {
        if (!part.isEmpty()) {
          ints.add(Integer.valueOf(part));
        }
      }

      return ints;
    };

    Function<List<Integer>, Integer> sum = (nums) -> nums.stream().reduce(0, Integer::sum);

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
