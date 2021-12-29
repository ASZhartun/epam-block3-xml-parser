package textReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Text-file reader, storage text as String, give access to content
 */
public class TextReader {
    private StringBuilder content;
    private String text;

    /**
     * Initialize StringBuilder content and fill it by content of file that specify by path.
     *
     * @param path - path, where's file locating.
     */
    public TextReader(String path) {
        this.content = new StringBuilder();
        readTextFile(path);
        this.text = this.content.toString();
    }

    public TextReader() {

    }

    /**
     * Reads file by path and put content to StringBuilder content.
     *
     * @param path - path, where's file locating.
     */
    private void readTextFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                this.content.append(line);
                this.content.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist!");
        } catch (IOException e) {
            System.out.println("Troubles with line readings!");
        }
    }

    /**
     * Rewrite content by reading file by specific path.
     *
     * @param path - path, where's new file locating.
     */
    public void getFileContent(String path) {
        readTextFile(path);
        this.text = this.content.toString();
    }

    /**
     * Get text of file was read.
     *
     * @return String with content of file.
     */
    public String getText() {
        return text;
    }

}
