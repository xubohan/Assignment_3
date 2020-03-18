import com.sun.source.tree.WhileLoopTree;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/** 
 * Class responsible for loading
 * book data from file.
 */
public class LibraryFileLoader{

    /**
     * Contains all lines read from a book data file using
     * the loadFileContent method.
     * 
     * This field can be null if loadFileContent was not called
     * for a valid Path yet.
     * 
     * NOTE: Individual line entries do not include line breaks at the 
     * end of each line.
     */
    private List<String> fileContent;

    /** Create a new loader. No file content has been loaded yet. */
    public LibraryFileLoader() { fileContent = null;}

    /**
     * Load all lines from the specified book data file and
     * save them for later parsing with the parseFileContent method.
     * 
     * This method has to be called before the parseFileContent method
     * can be executed successfully.
     * 
     * @param fileName file path with book data
     * @return true if book data could be loaded successfully, false otherwise
     * @throws NullPointerException if the given file name is null
     */
    public boolean loadFileContent(Path fileName) {
        Objects.requireNonNull(fileName, "Given filename must not be null.");
        boolean success = false;

        try {
            fileContent = Files.readAllLines(fileName);
            success = true;
        } catch (IOException | SecurityException e) {
            System.err.println("ERROR: Reading file content failed: " + e);
        }

        return success;
    }

    /**
     * Has file content been loaded already?
     * @return true if file content has been loaded already.
     */
    public boolean contentLoaded() {
        return fileContent != null;
    }

    /**
     * Parse file content loaded previously with the loadFileContent method.
     * 
     * @return books parsed from the previously loaded book data or an empty list
     * if no book data has been loaded yet.
     * @throws NullPointerException When the content is null
     */
    public List<BookEntry> parseFileContent() {
        if (fileContent == null) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.err.println("ERROR: No content loaded before parsing.");
                return new ArrayList<>();
            }
        }
        fileContent = skipFirstLine(fileContent);
        Iterator<String> iterator = fileContent.iterator();
        ArrayList<BookEntry> booksHere = new ArrayList<>();

        while (iterator.hasNext()) {
                String[] tempSave = iterator.next().split(",");
                String[] authors = tempSave[1].split("-");
                booksHere.add(new BookEntry(tempSave[0], authors,
                        Float.parseFloat(tempSave[2]),
                        tempSave[3], Integer.parseInt(tempSave[4])));
        }
        return booksHere;
    }

    /**
     * a helper function for skipping reading first line in file
     * @param arr original file
     * @return skipping first line and remaining parts are the same
     */
    private ArrayList<String> skipFirstLine (List<String> arr) {
        int i = 0;
        ArrayList<String> arrayList = new ArrayList<>();

        for (String arr1 : arr) {
            if (i == 0) { i ++; }
            else {
                arrayList.add(arr1);
            }
        }
        return arrayList;
    }

}
