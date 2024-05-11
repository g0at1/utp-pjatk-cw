package zad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private HashMap<String, List<String>> anagrams = new HashMap<>();
    private List<String> wordsList = new ArrayList<>();

    public Anagrams(String source) {
        try {
            URL url = new URL(source);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = br.readLine()) != null){
                wordsList.add(line);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        groupAnagrams();
    }

    public List<List<String>> getSortedByCount() {
        List<Map.Entry<String, List<String>>> sortedEntries = anagrams.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))
                .collect(Collectors.toList());

        List<List<String>> sortedAnagrams = sortedEntries.stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return sortedAnagrams;
    }

    private static String sortChars(String input) {
        char[] output = input.toCharArray();
        Arrays.sort(output);
        return new String(output);
    }

    public void groupAnagrams() {
        for (String word : wordsList) {
            String sortedChars = sortChars(word);
            System.out.println(sortedChars);
            if (anagrams.containsKey(sortedChars)) {
                anagrams.get(sortedChars).add(word);
            } else {
                List<String> tmp = new ArrayList<>();
                tmp.add(word);
                anagrams.put(sortedChars, tmp);
            }
        }
    }
}
