//Social Media Username Availability Checker
import java.util.*;

public class UsernameChecker {

    HashMap<String, Integer> usernameToUserId = new HashMap<>();
    HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameToUserId.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!usernameToUserId.containsKey(candidate))
                suggestions.add(candidate);
        }

        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        String result = "";
        int max = 0;

        for (String user : attemptFrequency.keySet()) {
            int count = attemptFrequency.get(user);
            if (count > max) {
                max = count;
                result = user;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        UsernameChecker system = new UsernameChecker();

        system.usernameToUserId.put("john_doe", 1);

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.checkAvailability("jane_smith"));

        System.out.println(system.suggestAlternatives("john_doe"));

        System.out.println(system.getMostAttempted());
    }
}
