import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()
    -> {
        return "gaurav";
    }).thenApply(name -> {
        return name.toUpperCase();
    }).thenApply(name -> {
        return name + " is a good boy";
    });
    future.join();
    future.thenAccept(result -> {
        System.out.println("Result: " + result);
    });
}
    
    
}
