import java.io.*;
import java.nio.file.*;


public class FileHandlingUtility {

    // ─────────────────────────────────────────────
    // 1. WRITE – Create a new file and write content
    // ─────────────────────────────────────────────
    public static void writeFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
        System.out.println("[WRITE] File created: " + filename);
    }

    // ─────────────────────────────────────────────
    // 2. READ – Read and display file contents
    // ─────────────────────────────────────────────
    public static void readFile(String filename) throws IOException {
        System.out.println("\n[READ] Contents of " + filename + ":");
        System.out.println("─────────────────────────────");
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        System.out.println("─────────────────────────────");
    }

    // ─────────────────────────────────────────────
    // 3. MODIFY – Replace a word/phrase in the file
    // ─────────────────────────────────────────────
    public static void modifyFile(String filename, String oldText, String newText) throws IOException {
        Path path = Paths.get(filename);
        String content = new String(Files.readAllBytes(path));
        content = content.replace(oldText, newText);
        Files.write(path, content.getBytes());
        System.out.println("\n[MODIFY] Replaced \"" + oldText + "\" with \"" + newText + "\" in " + filename);
    }

    // ─────────────────────────────────────────────
    // 4. APPEND – Add new lines to an existing file
    // ─────────────────────────────────────────────
    public static void appendFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.newLine();
        writer.write(content);
        writer.close();
        System.out.println("\n[APPEND] Content appended to: " + filename);
    }

    // ─────────────────────────────────────────────
    // 5. DELETE – Delete the file
    // ─────────────────────────────────────────────
    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("\n[DELETE] File deleted: " + filename);
        } else {
            System.out.println("\n[DELETE] Failed to delete: " + filename);
        }
    }

    // ─────────────────────────────────────────────
    // MAIN – Run all operations in sequence
    // ─────────────────────────────────────────────
    public static void main(String[] args) {
        String filename = "sample.txt";

        try {
            // Step 1: Write initial content
            writeFile(filename,
                "CODTECH Java Internship\n" +
                "Task 1: File Handling Utility\n" +
                "Author: Sheela Bharath Teja Reddy\n" +
                "Status: In Progress"
            );

            // Step 2: Read the file
            readFile(filename);

            // Step 3: Append more content
            appendFile(filename, "Language: Java\nInstitute: GITAM University");

            // Step 4: Read again after append
            readFile(filename);

            // Step 5: Modify a value
            modifyFile(filename, "In Progress", "Completed");

            // Step 6: Final read to confirm modification
            readFile(filename);

            // Step 7: Delete the file
            deleteFile(filename);

        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
