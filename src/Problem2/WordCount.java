package Problem2;

import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String filePath = "path/to/your/large/file.txt";
        int maxMemoryInBytes = 10 * 1024 * 1024; // 10 MB
        int chunkSize = maxMemoryInBytes / 2;

        Map<String, Integer> wordCounts = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder chunkBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                chunkBuilder.append(line).append(" ");
                if (chunkBuilder.length() >= chunkSize) {
                    processChunk(chunkBuilder.toString(), wordCounts);
                    chunkBuilder.setLength(0);
                }
            }
            if (chunkBuilder.length() > 0) {
                processChunk(chunkBuilder.toString(), wordCounts);
            }
        }

        List<Map.Entry<String, Integer>> sortedWordCounts = new ArrayList<>(wordCounts.entrySet());
        sortedWordCounts.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Integer> entry : sortedWordCounts) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void processChunk(String chunk, Map<String, Integer> wordCounts) {
        String[] words = chunk.split("\\s+");
        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-z0-9]", "");
            int count = wordCounts.getOrDefault(word, 0);
            wordCounts.put(word, count + 1);
        }
    }
}
