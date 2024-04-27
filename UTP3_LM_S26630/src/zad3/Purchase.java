/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad3;

public class Purchase {
  private String clientId;
  private String fullName;
  private String productName;
  private double price;
  private double amount;

  public Purchase(String clientId, String fullName, String productName,
                  double price, double amount) {
    this.clientId = clientId;
    this.fullName = fullName;
    this.productName = productName;
    this.price = price;
    this.amount = amount;
  }

  public String getClientId() { return clientId; }

  public double getAmount() { return amount; }

  public double getPrice() { return price; }

  public String getFullName() { return fullName; }

  public double getTotalCost() { return this.getPrice() * this.getAmount(); }

  @Override
  public String toString() {
    return clientId + ";" + fullName + ";" + productName + ";" + price + ";" +
        amount;
  }
}
