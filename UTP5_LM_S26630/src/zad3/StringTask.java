package zad3;

class StringTask implements Runnable {

  private TaskState state;
  private String result;
  private String input;
  private Thread thread;
  private int cycles;

  public StringTask(String input, int cycles) {
    this.input = input;
    this.cycles = cycles;
    this.thread = new Thread(this);
    this.state = TaskState.CREATED;
  }

  public String getResult() {
    return this.result;
  }

  public TaskState getState() {
    return this.state;
  }

  public void start() {
    this.thread.start();
  }

  public void abort() {
    this.thread.interrupt();
  }

  public boolean isDone() {
    return this.state == TaskState.ABORTED || this.state == TaskState.READY;
  }

  @Override
  public void run() {
    String result = "";

    for (int i = 0; i < this.cycles; i++) {
      if (Thread.currentThread().isInterrupted()) {
        this.state = TaskState.ABORTED;
        return;
      }

      result += this.input;
    }

    this.result = result;
    this.state = TaskState.READY;
  }
}
