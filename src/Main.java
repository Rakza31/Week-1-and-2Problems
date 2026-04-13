//Multi-Level Cache System with Hash Tables
import java.util.*;
public class Main {

    LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10, 0.75f, true);

    HashMap<String, String> L2 = new HashMap<>();

    public String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            System.out.println("L1 HIT");
            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {
            System.out.println("L2 HIT");

            String data = L2.get(videoId);
            L1.put(videoId, data);
            return data;
        }

        System.out.println("Database HIT");

        String data = "VideoData_" + videoId;

        L2.put(videoId, data);

        return data;
    }

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video1"));
    }
}
