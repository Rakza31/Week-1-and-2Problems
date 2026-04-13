//Autocomplete System for Search Engine
import java.util.*;

public class Autocomplete {

    HashMap<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query,
                frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        List<String> results = new ArrayList<>();

        for (String q : frequency.keySet()) {

            if (q.startsWith(prefix))
                results.add(q);
        }

        results.sort((a, b) ->
                frequency.get(b) - frequency.get(a));

        return results.subList(0,
                Math.min(10, results.size()));
    }

    public static void main(String[] args) {

        Autocomplete a = new Autocomplete();

        a.addQuery("java tutorial");
        a.addQuery("javascript");
        a.addQuery("java download");

        System.out.println(a.search("jav"));
    }
}
