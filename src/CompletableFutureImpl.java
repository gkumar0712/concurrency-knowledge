import java.util.concurrent.CompletableFuture;

public class CompletableFutureImpl {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<String> customer = CompletableFuture.supplyAsync(()  -> {
                sleep(2000);
                return "Customer OK";
        });
        CompletableFuture<String> inventory = CompletableFuture.supplyAsync(()  -> {
                sleep(2000);
                return "Inventory OK";
        });

        CompletableFuture<String> payment = CompletableFuture.supplyAsync(()  -> {
                sleep(5000);
                return "Payment OK";
        });

        CompletableFuture<String> customerInventory =
        customer.thenCombine(
                inventory,
                (customerResult, inventoryResult) ->
                        customerResult + " | " + inventoryResult
        );
        CompletableFuture<String> finalResult =
        customerInventory.thenCombine(
                payment,
                (customerInventoryResult, paymentResult) ->
                        customerInventoryResult +
                        " | " +
                        paymentResult
        );
        System.out.println(finalResult.join());
        
        
long end = System.currentTimeMillis();

System.out.println(
        "Total time: " +
        (end - start) +
        " ms"
);
        

    }

    static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    
    
}
