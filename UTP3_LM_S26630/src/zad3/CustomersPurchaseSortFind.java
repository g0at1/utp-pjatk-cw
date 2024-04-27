/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

  private List<Purchase> purchases = new ArrayList<>();

  public void readFile(String file) {
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        String[] val = line.split(";");
        String clientId = val[0];
        String fullName = val[1];
        String productName = val[2];
        double price = Double.parseDouble(val[3]);
        double amount = Double.parseDouble(val[4]);
        Purchase purchase =
            new Purchase(clientId, fullName, productName, price, amount);
        this.purchases.add(purchase);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void showSortedBy(String sortType) {
    System.out.println(sortType);
    List<Purchase> sortedValues = new ArrayList<>(this.purchases);

    switch (sortType) {
    case "Nazwiska":
      sortedValues.sort(Comparator.comparing(Purchase::getFullName)
                          .thenComparing(Purchase::getClientId));

      for (Purchase purchase : sortedValues) {
        System.out.println(purchase);
      }

      break;
    case "Koszty":
      sortedValues.sort(Comparator.comparingDouble(Purchase::getTotalCost)
                          .reversed()
                          .thenComparing(Purchase::getClientId));

      for (Purchase purchase : sortedValues) {
        System.out.println(purchase + " (koszt: " + purchase.getTotalCost() +
                           ")");
      }

      break;
    }

    System.out.println();
  }

  public void showPurchaseFor(String clientId) {
    System.out.println("Klient " + clientId);

    for (Purchase purchase : this.purchases) {
      if (purchase.getClientId().equals(clientId)) {
        System.out.println(purchase);
      }
    }

    System.out.println();
  }
}
