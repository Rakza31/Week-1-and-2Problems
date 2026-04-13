//Two-Sum Problem Variants for Financial Transactions
import java.util.*;

public class TwoSumFraud {

    public static void findTwoSum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {

            int complement = target - num;

            if (map.containsKey(complement)) {

                System.out.println("Pair: "
                        + num + " + " + complement);
            }

            map.put(num, 1);
        }
    }

    public static void main(String[] args) {

        int[] transactions = {500, 300, 200, 700};

        findTwoSum(transactions, 500);
    }
}
