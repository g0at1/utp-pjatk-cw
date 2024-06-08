package zad4;

import javax.swing.*;

public class TaskWorker extends SwingWorker<String, Void> {

  @Override
  protected String doInBackground() throws Exception {
    int durationTime = (int) (Math.random() * 10 + 1) * 1000;
    Thread.sleep(durationTime);
    return "Task completed in " + (durationTime / 1000) + " seconds";
  }

  @Override
  public String toString() {
    return isDone() ? "Task is completed" : "Task is still running";
  }
}
