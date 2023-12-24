package scheduler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter;
        ExecutorSchedulerService schedulerService = new ExecutorSchedulerService(12);
        while(true) {
            System.out.println("Menu:\n1. Add task\n2. Run scheduler\n3. Check the status of tasks\n4. Exit");
            counter = scanner.nextInt();
            switch (counter) {
                case (1): {
                    System.out.println("Enter the name of the task:");
                    String task = scanner.next();
                    System.out.println("Enter the execution time of the task in milliseconds:");
                    long executionTime = scanner.nextLong();
                    Task currentTask = new Task(task, executionTime);
                    schedulerService.addTask(currentTask);
                    break;
                }
                case(2): {
                    schedulerService.start();
                    break;
                }
                case (3): {
                    schedulerService.taskCheck();
                    break;
                }
                case (4): {
                    schedulerService.stop();
                    return;
                }
            }
        }
    }
}