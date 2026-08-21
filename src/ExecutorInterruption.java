import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorInterruption {
    public static void main (String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
        executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task: " +taskId + "completed by "  + 
                Thread.currentThread().getName());
        });
    }
    
}
}
