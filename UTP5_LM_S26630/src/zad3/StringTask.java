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
        this.state = TaskState.CREATED;
    }

    public String getResult() {
        return this.result;
    }

    public TaskState getState() {
        return this.state;
    }

    public void start() {
        if (this.state == TaskState.CREATED) {

            this.state = TaskState.RUNNING;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void abort() {
        if (this.state == TaskState.RUNNING) {

            this.thread.interrupt();
            this.state = TaskState.ABORTED;
        }
    }

    public boolean isDone() {
        return this.state == TaskState.ABORTED || this.state == TaskState.READY;
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.cycles; i++) {
            if (Thread.currentThread().isInterrupted()) {
                this.state = TaskState.ABORTED;

                return;
            }

            stringBuilder.append(this.input);
        }

        this.result = stringBuilder.toString();
        this.state = TaskState.READY;
    }
}
