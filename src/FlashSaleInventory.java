//E-commerce Flash Sale Inventory Manager
import java.util.*;

public class FlashSaleInventory {

    HashMap<String, Integer> stock = new HashMap<>();
    HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public synchronized String purchaseItem(String productId, int userId) {

        int available = stock.getOrDefault(productId, 0);

        if (available > 0) {
            stock.put(productId, available - 1);
            return "Success! Remaining: " + (available - 1);
        }

        waitingList.putIfAbsent(productId, new LinkedList<>());
        waitingList.get(productId).add(userId);

        return "Added to waiting list position "
                + waitingList.get(productId).size();
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public static void main(String[] args) {

        FlashSaleInventory inv = new FlashSaleInventory();

        inv.stock.put("IPHONE15", 3);

        System.out.println(inv.purchaseItem("IPHONE15", 101));
        System.out.println(inv.purchaseItem("IPHONE15", 102));
        System.out.println(inv.purchaseItem("IPHONE15", 103));
        System.out.println(inv.purchaseItem("IPHONE15", 104));

        System.out.println("Stock left: " + inv.checkStock("IPHONE15"));
    }
}
