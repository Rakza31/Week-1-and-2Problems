//Plagiarism Detection System
import java.util.*;

public class PlagiarismDetector {

    HashMap<String, Set<Integer>> index = new HashMap<>();

    public void addDocument(int docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - 5; i++) {

            String gram = String.join(" ",
                    Arrays.copyOfRange(words, i, i + 5));

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    public Map<Integer, Integer> analyze(String text) {

        HashMap<Integer, Integer> matches = new HashMap<>();

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - 5; i++) {

            String gram = String.join(" ",
                    Arrays.copyOfRange(words, i, i + 5));

            if (index.containsKey(gram)) {

                for (int doc : index.get(gram)) {
                    matches.put(doc,
                            matches.getOrDefault(doc, 0) + 1);
                }
            }
        }

        return matches;
    }

    public static void main(String[] args) {

        PlagiarismDetector p = new PlagiarismDetector();

        p.addDocument(1, "this is a sample document for plagiarism detection system");

        System.out.println(p.analyze("this is a sample document for plagiarism detection"));
    }
}
