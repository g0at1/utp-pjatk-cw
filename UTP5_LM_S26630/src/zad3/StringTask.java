package zad3;

class StringTask implements Runnable {
    private String result;
    private TaskState state;
    private String inputString;
    private int repetitions;
    private Thread workerThread;

    public StringTask(String inputString, int repetitions) {
        this.inputString = inputString;
        this.repetitions = repetitions;
        this.state = TaskState.CREATED;
    }

    public String getResult() {
        return result;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        if (state == TaskState.CREATED) {
            state = TaskState.RUNNING;
            workerThread = new Thread(this);
            workerThread.start();
        }
    }

    public void abort() {
        if (state == TaskState.RUNNING) {
            workerThread.interrupt();
            state = TaskState.ABORTED;
        }
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            if (Thread.currentThread().isInterrupted()) {
                state = TaskState.ABORTED;
                return;
            }
            builder.append(inputString);
        }
        result = builder.toString();
        state = TaskState.READY;
    }
}
