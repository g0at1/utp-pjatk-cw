/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad4;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new TaskManager().setVisible(true);
      }
    });
  }
}
