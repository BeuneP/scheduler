package scheduler;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

class ExecutorSchedulerService {
    private ExecutorService executorService;
    private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public ExecutorSchedulerService(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
        if (isRunning.get()) {
            executorService.submit(executeTask(task));
        }
    }

    public Runnable executeTask(Task task) {
        return () -> {
            task.setStatus("In progress");
            try {
                TimeUnit.MILLISECONDS.sleep(task.getTime());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            task.setStatus("Completed");
        };
    }

    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            for (Task task : taskQueue) {
                if (Objects.equals(task.getStatus(), "Waiting")) {
                    executorService.submit(executeTask(task));
                }
            }
        }
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public void taskCheck() {
        for (Task task : taskQueue) {
            System.out.println("Task " + task.getName() + ", status: " + task.getStatus());
        }
    }
}