import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureRetryDemo {
    static int attempt = 0;

    public static void main(String[] args) {
        CompletableFuture<String> result = callFlightApiwithRetry();
        try {
            System.out.println ("Final Result:" + result.get());
        } catch (Exception e) {
            System.out.println ("Final Result:" + e.getCause());
        }
    }

    private static CompletableFuture<String> callFlightApiwithRetry() {
        return callFlightApi();
        
    }

    private static CompletableFuture<String> callFlightApi() {
       return CompletableFuture.supplyAsync(()-> {
            attempt ++;
            System.out.println ("Calling Flight API, attempt: " + attempt); 
            //if (attempt < 3) {
              //  throw new RuntimeException("Flight API failed");
            //}
            sleep(5000);
            return "Flight Details Found";
       }).orTimeout(2, TimeUnit.SECONDS);
    }

    static void sleep(long milliseconds) {

    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
}
