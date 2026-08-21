public class ConcurrencyApplication {
    static String customerService() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Customer Service";
    }
    static String inventoryService() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Inventory Service";
    }
    static String paymentService() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Payment Service";
    }
    static String shippingService() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Shipping Service";
    }
    static String discountService() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Discount Service";
    }

    public static void main(String[] args) {
        customerService();
        inventoryService();
        paymentService();
        shippingService();
        discountService();
    }
}
