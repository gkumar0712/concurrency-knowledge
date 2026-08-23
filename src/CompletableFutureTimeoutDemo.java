import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeoutDemo {

    public static void main(String[] args) {
        CompletableFuture<String> payment = CompletableFuture.supplyAsync(()->{
            System.out.println("Payment Started");
            sleep(5000);
            System.out.println("Payment Completed");
            return "Payment successful";
        });

        //payment.orTimeout(2,TimeUnit.SECONDS);
        payment.completeOnTimeout(
    "Payment Pending",
    4,
    TimeUnit.SECONDS
);
        try {
    System.out.println(payment.join());
} catch (Exception e) {
    System.out.println(
            "Payment Timeout: " + e
    );
}
System.out.println("Waiting to observe worker...");

sleep(4000);

System.out.println("Main Completed");
    }

    static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
}
}
