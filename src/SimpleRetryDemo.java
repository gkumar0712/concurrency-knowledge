public class SimpleRetryDemo {

    static int attempt = 0;

    static String callFlightApi() {
        attempt++;
        System.out.println("Calling Flight API, Attempt:"+ attempt);
        if (attempt < 3) {
            throw new RuntimeException("Flight API failed");
        }
        return "Flight Details Found";
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int maxAttempts = 4;
        
        for (int i= 1;i<= maxAttempts;i++) {
            
            try{
                String result = callFlightApi();
                System.out.println(result);
                break;
            } catch (RuntimeException e) {
                System.out.println("Attempt " + i + " failed: " + e.getMessage());
                if (i< maxAttempts) {
                    System.out.println("Retrying...");
                } try {
                    //Exponential backoff without jittering
                    //long delay = 1000L * (1L << (i - 1));
                    //Exponential backoff jittering
                    long baseDelay = 1000L * (1L << (i - 1));
                    long jitter = (long) (Math.random() * 500);
                    long delay = baseDelay + jitter;
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        break;
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Total time: " +(end - start) +" ms");
        
    }
}
