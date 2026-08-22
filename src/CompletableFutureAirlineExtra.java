import java.util.concurrent.CompletableFuture;

public class CompletableFutureAirlineExtra {
    public static void main(String[] args) {
        CompletableFuture <String> flightDetails = CompletableFuture.supplyAsync(()->{
                sleep(2000);
                return "Flight-100";
        });
        CompletableFuture<String> bookingDetails = CompletableFuture.supplyAsync(()-> {
            sleep(2000);
            return "Booking-100";
        });
        CompletableFuture<String> seatReservationDetails = 
            flightDetails.thenCompose(flightId -> CompletableFuture.supplyAsync(()-> {
                sleep(2000);
                return "Seat available for " + flightId;
            }));

            CompletableFuture<String> passenger = CompletableFuture.supplyAsync(()-> {
            sleep(2000);
            return "XYZ";
        });

        CompletableFuture<String> booking = seatReservationDetails.thenCombine(passenger, (seatResult, passengerDetails) -> seatResult + "|" + passengerDetails);
            
        System.out.println(booking.join());
    }

    static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
}
}
