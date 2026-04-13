//Real-Time Analytics Dashboard for Website Traffic
import java.util.*;

public class AnalyticsDashboard {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> trafficSource = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSource.put(source,
                trafficSource.getOrDefault(source, 0) + 1);
    }

    public void showDashboard() {

        System.out.println("Page Views: " + pageViews);
        System.out.println("Unique Visitors: " + uniqueVisitors);
        System.out.println("Traffic Sources: " + trafficSource);
    }

    public static void main(String[] args) {

        AnalyticsDashboard a = new AnalyticsDashboard();

        a.processEvent("/news", "user1", "google");
        a.processEvent("/news", "user2", "facebook");
        a.processEvent("/sports", "user3", "direct");

        a.showDashboard();
    }
}
