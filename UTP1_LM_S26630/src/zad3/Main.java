/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad3;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter(x -> x.startsWith("WAW"))
            .map(x -> {
                String[] xArr = x.split(" ");
                String destination = xArr[1];
                int priceInEUR = Integer.parseInt(xArr[2]);
                int priceInPLN = (int)(priceInEUR * ratePLNvsEUR);
                return String.format("to %s - price in PLN: %d", destination, priceInPLN);
            })
            .toList();
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */

    for (String r : result) System.out.println(r);
  }
}
