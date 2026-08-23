import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionDemo {

    public static void main(String[] args) {
        CompletableFuture <String> flightDetails = CompletableFuture.supplyAsync(()->{
                sleep(2000);
                return "Flight-100";
        });
        
        CompletableFuture<String> seatAvailibilty = 
            flightDetails.thenCompose(flightId -> CompletableFuture.supplyAsync(()-> {
                sleep(2000);
                throw new RuntimeException("Seat service is down for " + flightId);
            }));

            seatAvailibilty.whenComplete((result, exception) -> {
                if (exception != null) {
                    System.out.println("Seat API Monitoring Failed");
                } else {
                    System.out.println("Seat API Monitoring Successful");
                }
            }

        );

            CompletableFuture<String> bookingDecision = seatAvailibilty.handle((seatResult, ex) ->{
                if (ex != null) {
                    System.out.println("Cannot Continue Booking:" +ex.getMessage());
                    return "Booking Failed";
                }
                return "Booking Successful";
            } );

           //CompletableFuture<String> result =  seatAvailibilty.exceptionally(ex ->{
            
            //return "Seat service is down";
           //});
           System.out.println(bookingDecision.join());
    }

    static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    
}
