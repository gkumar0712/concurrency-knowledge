import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptional {
    public static void main(String[] args) {
        CompletableFuture<String> flight = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Flight not available");
        });
        CompletableFuture<String> result = flight.exceptionally(ex -> {
            System.out.println("Flight Failed: " + ex.getMessage());
            return "Alternative Flight";
        });

        CompletableFuture<String> result2 = flight.handle((value, ex) -> {
            if (ex != null) {
                System.out.println("Flight Failed: " + ex.getMessage());
                return "Alternative Flight";
            }
            return value;
        });
        System.out.println(result2.join());
    }
}
