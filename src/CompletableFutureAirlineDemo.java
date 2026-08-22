import java.util.concurrent.CompletableFuture;

public class CompletableFutureAirlineDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<String> flightDetails = CompletableFuture.supplyAsync(()->{
                sleep(2000);
                return "Flight-100";
        });
        CompletableFuture<String> passengerDetails = CompletableFuture.supplyAsync(()->{
                sleep(2000);
                return "Passenger-100";
        });
        CompletableFuture<String> bookingDetails = flightDetails.thenCombine(passengerDetails,  (flight, passenger) -> {
            return "Booking for " + flight + " with " + passenger;
        });
        System.out.println(bookingDetails.join());
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
