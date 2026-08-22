import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureDemo {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<String> customer = executor.submit(() -> {
            Thread.sleep(2000);
            return "Customer OK";
        });

        Future<String> inventory = executor.submit(() -> {
            Thread.sleep(2000);
            return "Inventory OK";
        });

        Future<String> payment = executor.submit(() -> {
            Thread.sleep(2000);
            return "Payment OK";
        });

        System.out.println(customer.get(100,TimeUnit.SECONDS));
        System.out.println(inventory.get(100,TimeUnit.SECONDS));
        System.out.println(payment.get(100,TimeUnit.SECONDS));
    }
    
}
