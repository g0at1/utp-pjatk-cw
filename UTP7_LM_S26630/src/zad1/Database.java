package zad1;

import java.awt.event.WindowAdapter;
import java.sql.*;
import java.util.List;
import javax.swing.*;

public class Database {
  private String url;
  private TravelData travelData;

  public Database(String url, TravelData travelData) {
    this.url = url;
    this.travelData = travelData;
  }

  public void create() {
  }

  public void showGui() {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Travel Offers");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String[] columnNames = { "Country", "Start Date", "End Date",
          "Place", "Price", "Currency" };
      String[][] data = retrieveOffers();

      JTable table = new JTable(data, columnNames);
      frame.add(new JScrollPane(table));

      frame.pack();
      frame.setVisible(true);
    });
  }
}
