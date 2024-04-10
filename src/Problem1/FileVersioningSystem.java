package Problem1;

import java.io.*;
import java.util.*;

// Represents a file version
class FileVersion {
    private final String content;

    public FileVersion(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Represents the storage for file versions and deltas
class VersionStorage {
    private final List<FileVersion> versions = new ArrayList<>();

    // Add a new version of the file
    public void addVersion(FileVersion version) {
        versions.add(version);
    }

    // Generate a new version by applying deltas
    public FileVersion generateVersion(List<String> deltas) {
        StringBuilder contentBuilder = new StringBuilder();
        FileVersion baseVersion = versions.get(0); // Base version

        // Apply deltas to the base version
        for (String delta : deltas) {
            contentBuilder.append(applyDelta(baseVersion.getContent(), delta));
        }

        return new FileVersion(contentBuilder.toString());
    }

    // Apply a single delta to the content
    private String applyDelta(String content, String delta) {
        // Implement logic to apply delta (for demonstration, simply concatenate)
        return content + delta;
    }

    // Save the storage to a file
    public void saveToFile(String filePath) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(this);
        }
    }

    // Load the storage from a file
    public static VersionStorage loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (VersionStorage) inputStream.readObject();
        }
    }
}

public class FileVersioningSystem {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create and add some file versions
        FileVersion version1 = new FileVersion("Content of version 1");
        FileVersion version2 = new FileVersion("Content of version 2");
        FileVersion version3 = new FileVersion("Content of version 3");

        VersionStorage storage = new VersionStorage();
        storage.addVersion(version1);
        storage.addVersion(version2);
        storage.addVersion(version3);

        // Generate a new version by applying deltas
        List<String> deltas = Arrays.asList("Delta for version 4", "Another delta for version 4");
        FileVersion version4 = storage.generateVersion(deltas);
        storage.addVersion(version4);

        // Save the storage to a file
        String filePath = "version_storage.ser";
        storage.saveToFile(filePath);

        // Load the storage from the file
        VersionStorage loadedStorage = VersionStorage.loadFromFile(filePath);

        // Example usage
        System.out.println("Content of version 2: " + loadedStorage.generateVersion(Collections.singletonList("Delta for version 2")).getContent());
    }
}
