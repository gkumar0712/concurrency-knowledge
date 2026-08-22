import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithDifferentMethods {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<String> customer = CompletableFuture.supplyAsync(()-> {
                sleep(2000);
                return "Cust-100";
        });
        CompletableFuture<String> orders = customer.thenCompose(customerId -> CompletableFuture
                .supplyAsync(()-> 
        {
                sleep(2000);
                return "Order-100 for " + customerId;
        }));

        System.out.println(orders.join());
        
        
        
        long end = System.currentTimeMillis();

        System.out.println("Total time: " +(end - start) +" ms");
        

    }

    static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    
    
}
