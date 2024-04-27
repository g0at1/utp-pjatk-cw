package zad2;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class ProgLang {

  private Map<String, Set<String>> langsMap = new HashMap<>();
  private Map<String, Set<String>> progsMap = new HashMap<>();

  public ProgLang(String nazwaPliku) throws IOException {
    try {
      FileReader fr = new FileReader(nazwaPliku);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\t");
        String language = parts[0];
        Set<String> programmers = new HashSet<>(Arrays.asList(parts).subList(1, parts.length));
        langsMap.put(language, programmers);

        for (String programmer : programmers) {
          progsMap.computeIfAbsent(programmer, k -> new HashSet<>()).add(language);
        }
      }
    } catch (IOException e) {
      throw new IOException("Error while loading file", e);
    }
  }

  public Map<String, Set<String>> getLangsMap() {
    return new HashMap<>(langsMap);
  }

  public Map<String, Set<String>> getProgsMap() {
    return new HashMap<>(progsMap);
  }

  public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
    return sorted(langsMap, (e1, e2) -> {
      int compare = Integer.compare(e2.getValue().size(), e1.getValue().size());

      return compare != 0
              ? compare
              : e1.getKey().compareTo(e2.getKey());
    });
  }

  public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
    return sorted(progsMap, (e1, e2) -> {
      int compare = Integer.compare(e2.getValue().size(), e1.getValue().size());

      return compare != 0
              ? compare
              : e1.getKey().compareTo(e2.getKey());
    });
  }

  public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
    return filtered(progsMap, e -> e.getValue().size() > n);
  }

  private Map<String, Set<String>> sorted(Map<String, Set<String>> map, Comparator<Map.Entry<String, Set<String>>> comparator) {
    return map.entrySet()
            .stream()
            .sorted(comparator)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }

  private Map<String, Set<String>> filtered(Map<String, Set<String>> map, Predicate<Map.Entry<String, Set<String>>> predicate) {
    return map.entrySet()
            .stream()
            .filter(predicate)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }
}
