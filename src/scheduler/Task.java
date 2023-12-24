package scheduler;

class Task {
    private String name;
    private String status;
    private long executionTimeMillis;
    public Task(String name, long executionTimeMillis) {
        this.name = name;
        this.status = "Not being executed";
        this.executionTimeMillis = executionTimeMillis;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public long getTime() {
        return executionTimeMillis;
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("\nTask " + this.name + " changed the status to " + this.status);
    }
}