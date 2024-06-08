package zad4;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public class TaskManager extends JFrame {

  private final JList<TaskWorker> taskList;
  private final DefaultListModel<TaskWorker> taskListModel;

  public TaskManager() {

    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    taskListModel = new DefaultListModel<>();
    taskList = new JList<>(taskListModel);
    taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    JButton startButton = new JButton("Start Task");
    JButton resultButton = new JButton("Show Result");
    JButton cancelButton = new JButton("Cancel Task");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(resultButton);
    buttonPanel.add(cancelButton);

    add(new JScrollPane(taskList), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    startButton.addActionListener(e -> {
      TaskWorker task = new TaskWorker();
      taskListModel.addElement(task);
      task.execute();
    });

    resultButton.addActionListener(e -> {
      TaskWorker task = taskList.getSelectedValue();
      if (task != null && task.isDone()) {
        try {
          String result = task.get();
          JOptionPane.showMessageDialog(TaskManager.this, "Result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
          JOptionPane.showMessageDialog(
              TaskManager.this, "Failed to get result: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(TaskManager.this,
            "Task is not completed yet.");
      }
    });

    cancelButton.addActionListener(e -> {
      TaskWorker task = taskList.getSelectedValue();
      if (task != null) {
        task.cancel(true);
      }
    });
  }
}
