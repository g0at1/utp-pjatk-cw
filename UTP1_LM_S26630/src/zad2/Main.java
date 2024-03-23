/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad2;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations).when(x -> x.startsWith("WAW"))
            .mapEvery(x -> {
              String[] xArr = x.split(" ");
              String destination = xArr[1];
              int priceInEUR = Integer.parseInt(xArr[2]);
              int priceInPLN = (int)(priceInEUR * xrate);
              return String.format("to %s - price in PLN: %d", destination, priceInPLN);
            });
  }

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
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
