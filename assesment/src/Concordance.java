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
        Map<String, WordInfo> concordance = new HashMap<>();

        // Initialize sentence counter
        int sentenceNum = 1;

        // Iterate through each word in the text
        for (int i = 0; i < words.length; i++) {
            // Clean the word by removing punctuation and converting to lowercase
            String word = words[i].replaceAll("[^a-zA-Z]", "").toLowerCase();

            // Skip empty words (in case of multiple punctuation marks)
            if (word.isEmpty()) continue;

            // Check if word is already in the concordance
            concordance.putIfAbsent(word, new WordInfo());
            WordInfo wordInfo = concordance.get(word);

            // Increment the frequency and add the current sentence number
            wordInfo.incrementFrequency();
            wordInfo.addSentenceNumber(sentenceNum);

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
            WordInfo wordInfo = concordance.get(key);
            System.out.println(key + " {" + wordInfo.getFrequency() + ":" +
                    String.join(",", wordInfo.getSentenceNumbers().stream().map(Object::toString).collect(Collectors.toList())) + "}");
        }
    }
}

class WordInfo {
    private int frequency;
    private final List<Integer> sentenceNumbers;

    public WordInfo() {
        this.frequency = 0;
        this.sentenceNumbers = new ArrayList<>();
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    public void addSentenceNumber(int sentenceNumber) {
        if (!this.sentenceNumbers.contains(sentenceNumber)) {
            this.sentenceNumbers.add(sentenceNumber);
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public List<Integer> getSentenceNumbers() {
        return sentenceNumbers;
    }
}
