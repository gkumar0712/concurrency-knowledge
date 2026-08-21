public class Main{
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Running on:" + Thread.currentThread().getName());
        });
        //thread.start();
        System.out.println("Main Thread:" + Thread.currentThread().getName());
        thread.run();
        // Why ExecutorService is better than thread class?
        //Manually created thread doesn't scale,
        // For 100 requests, 100 threads will be executed
        // ExecutorService manages a pool of threads and reuses them for multiple 
        // tasks, improving performance and resource utilization.
    }
}