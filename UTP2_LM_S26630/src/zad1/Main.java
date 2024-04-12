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
    Function<String, List<String>> flines = (fileName) -> {
      List<String> lines = null;
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
          lines.add(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return lines;
    };

    Function<List<String>, String> join = (strings) -> {
      StringBuilder sb = new StringBuilder();
      for (String str : strings) {
        sb.append(str);
      }
      return sb.toString();
    };

    Function<String, List<Integer>> collectInts = (text) -> {
      List<Integer> ints = new ArrayList<>();
      String[] parts = text.split("\\D+");
      for (String part : parts) {
        if (!part.isEmpty()) {
          ints.add(Integer.parseInt(part));
        }
      }
      return ints;
    };

    Function<List<Integer>, Integer> sum = (integers) -> {
      int s = 0;
      for (int num : integers) {
        s += num;
      }
      return s;
    };

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
