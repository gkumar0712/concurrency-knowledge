import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTimeoutDemo {
        
        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            Future<String> customer = executor.submit(() -> {
                System.out.println("Customer Started");
                Thread.sleep(2000);
                System.out.println("Customer Completed");
                return "Customer OK";
            });

            Future<String> inventory = executor.submit(() -> {
                System.out.println("Inventory Started");
                Thread.sleep(2000);
                System.out.println("Inventory Completed");  
                return "Inventory OK";
            });

            Future<String> payment = executor.submit(() -> {
                System.out.println("Payment Started");
                try {

        Thread.sleep(10000);

        System.out.println("Payment Completed");

        return "Payment OK";

    } catch (InterruptedException e) {

        System.out.println(
                "Payment Interrupted"
        );

        Thread.currentThread().interrupt();

        return "Payment Cancelled";
    }
    });
            

            try {
                System.out.println(customer.get(3,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.out.println("Customer Service Timeout");
            }

            try {
                System.out.println(inventory.get(3,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.out.println("Inventory Service Timeout");
            }

            try {
                System.out.println(payment.get(2,TimeUnit.SECONDS));
                
            } catch (TimeoutException e) {
                System.out.println("Payment Service Timeout");
                boolean cancelled = payment.cancel(true);
                System.out.println(
            "Payment cancelled: " + payment.isCancelled() + " | Cancelled: " + cancelled
    );
    System.out.println("Payment Done: " + payment.isDone());
            }

            finally {
                executor.shutdown();
            }
        }
    
}
