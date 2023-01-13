import java.util.*;
import java.util.stream.Collectors;

public class Concordance {
    public static void main(String[] args) {
        // Input text document
        String text = "Given an arbitrary text document written in English, " +
                "write a program that will generate a concordance, " +
                "i.e. an alphabetical list of all word occurrences, " +
                "labeled with word frequencies. " +
                "Bonus: label each word with the sentence numbers in which each occurrence appeared.";

        // Split the text into words
        String[] words = text.split("\\s+");

        // Create a map to store word frequencies and sentence numbers
        Map<String, Map<Integer, List<Integer>>> concordance = new HashMap<>();

        // Initialize sentence counter
        int sentenceNum = 1;

        // Iterate through each word in the text
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();

            // Check if word is already in the concordance
            if (!concordance.containsKey(word)) {
                // If not, add it to the concordance with a frequency of 1
                Map<Integer, List<Integer>> frequencyAndSentence = new HashMap<>();
                List<Integer> sentence = new ArrayList<>();
                sentence.add(sentenceNum);
                frequencyAndSentence.put(1, sentence);
                concordance.put(word, frequencyAndSentence);
            } else {
                // If so, increment the frequency
                Map<Integer, List<Integer>> frequencyAndSentence = concordance.get(word);
                List<Integer> sentence = frequencyAndSentence.get(frequencyAndSentence.keySet().iterator().next());
                int frequency = frequencyAndSentence.keySet().iterator().next()+1;
                sentence.add(sentenceNum);
                frequencyAndSentence.put(frequency, sentence);
                concordance.put(word, frequencyAndSentence);
            }

            // Check for end of sentence
            if (words[i].endsWith(".") || words[i].endsWith("!") || words[i].endsWith("?")) {
                sentenceNum++;
            }
        }

        // Sort the concordance alphabetically
        List<String> keys = new ArrayList<>(concordance.keySet());
        Collections.sort(keys);

        // Print the concordance
        for (String key : keys) {
            Map<Integer, List<Integer>> frequencyAndSentence = concordance.get(key);
            int frequency = frequencyAndSentence.keySet().iterator().next();
            List<Integer> sentence = frequencyAndSentence.get(frequency);
            System.out.println(key + " {" + frequency + ":" + String.join(",", sentence.stream().map(Object::toString).collect(Collectors.toList())) + "}");
        }
    }
}


