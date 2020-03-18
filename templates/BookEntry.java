import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {
    /** the title of the book **/
    private String title;

    /** the author or authors of the book **/
    private String[] authors;

    /**  a rating between 0 and 5 **/
    private float rating;

    /** an ISBN number **/
    private String ISBN;

    /** the number of pages **/
    private int pages;

    /**
     * this constructor is for recording the input and transit to private fields
     * @param title to record the title of the book
     * @param authors to record the author or authors of the book
     * @param rating to record a rating between 0 and 5
     * @param ISBN to record an ISBN number
     * @param pages to record the number of pages
     */
    public BookEntry(String title, String[] authors,
                     float rating, String ISBN, int pages){
        // This is for determining null value
        exceptionCollection (title, authors, rating, ISBN, pages);

        this.title = title;
        this.authors = authors.clone();
        this.rating = rating;
        this.ISBN = ISBN;
        this.pages = pages;
    }

    /**
     * a method to get book name
     * @return private instance field, title.
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * a method to get author name
     * @return private instance field, author.
     */
    public String[] getAuthors(){
        return this.authors;
    }

    /**
     * a method to get rating number
     * @return private instance field, rating.
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * a method to get ISBN number
     * @return private instance field, ISBN.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * a method to get page number
     * @return private instance field, pages.
     */
    public int getPages() {
        return this.pages;
    }

    /**
     * determining two sets of data are same or not
     * @param o any object
     * @return if it is equal it will return true
     */
    @Override
    public boolean equals(Object o) {
        // Comparing values by using Intellij Idea template
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookEntry bookEntry = (BookEntry) o;
        return Float.compare(bookEntry.rating, rating) == 0 &&
                pages == bookEntry.pages &&
                title.equals(bookEntry.title) &&
                Arrays.equals(authors, bookEntry.authors) &&
                ISBN.equals(bookEntry.ISBN);
    }

    /**
     * hashcode function
     * @return specific number
     */
    @Override
    public int hashCode() {
        // Generate a hashcode by using Intellij Idea template
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }

    /**
     * change data type to String
     * @return String in specific form
     */
    @Override
    public String toString(){
        // Generate a specific layout
        return  getTitle() + "\n" +
                "by "+ pureString(getAuthors()) +"\n"+
                "Rating: " + String.format("%.2f",getRating()) + "\n" +
                "ISBN: " + getISBN() + "\n" +
                getPages() + " pages";
    }

    /**
     * this method is for transforming String Arrays to String
     * @param authors authors of the book
     * @return a single line String
     */

    private String pureString(String[] authors) {
        String temp = "";
        for (int i = 0; i < authors.length; i++) {
            if (i == authors.length - 1) {
                temp += authors[i];
            } else {
                temp += authors[i] + ", ";
            }
        }
        return temp;
    }

    /**
     * this is helper function to detect potential exceptions
     * @param title the title of the book
     * @param authors the author or authors of the book
     * @param rating a rating between 0 and 5
     * @param ISBN an ISBN number
     * @param pages the number of pages
     */
    private void exceptionCollection (String title, String[] authors, float rating, String ISBN, int pages) {
        // This is for determining null value
        Objects.requireNonNull(title, "This is invalid input.");
        Objects.requireNonNull(authors, "This is invalid input.");
        Objects.requireNonNull(rating, "This is invalid input.");
        Objects.requireNonNull(ISBN, "This is invalid input.");
        Objects.requireNonNull(pages, "This is invalid input.");

        // This is for determining value in the bound
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("This is invalid input.");
        }
        if (pages <= 0) {
            throw new IllegalArgumentException("This is invalid input.");
        }
    }

}
