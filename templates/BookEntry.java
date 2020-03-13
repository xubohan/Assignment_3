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
     * This constructor is for recording the input and transit to private fields
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

    /** Default Constructor **/
    public BookEntry(){
        // Nothing to do here
        // Keep it empty
    }

    /**
     * A method to get book name
     * @return private instance field, title.
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * A method to get author name
     * @return private instance field, author.
     */
    public String[] getAuthors(){
        return this.authors;
    }

    /**
     * A method to get rating number
     * @return private instance field, rating.
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * A method to get ISBN number
     * @return private instance field, ISBN.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * A method to get page number
     * @return private instance field, pages.
     */
    public int getPages() {
        return this.pages;
    }

//    /**
//     * This equal method is for comparing the input and target values
//     * @param title compare with the title of the book
//     * @param authors compare with the author or authors of the book
//     * @param rating compare with a rating between 0 and 5
//     * @param ISBN compare with an ISBN number
//     * @param pages compare with the number of pages
//     * @return if fives fields are the same, it will return true.
//     */
//    public boolean equals (String title, String[] authors,
//                          float rating, String ISBN, int pages) {
//        exceptionCollection (title, authors, rating, ISBN, pages);
//
//        // Comparing with inferences
//        if (this.title == title && this.rating == rating && this.authors == authors
//            && this.ISBN == ISBN && this.pages == pages) {
//            return true;
//        }
//
//        // This is for comparing with target value
//        if (!this.title.equals(title) || this.rating != rating
//            || !this.ISBN.equals(ISBN) || this.pages != pages
//                || this.authors.length != authors.length) {
//            return false;
//        }
//        // Comparing with two String arrays, they are equal or not
//        return Arrays.equals(this.authors, authors);
//    }

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

    @Override
    public int hashCode() {
        // Generate a hashcode by using Intellij Idea template
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }
    //    @Override
//    public int hashCode() {
//        // Generate a hashcode by using own algorithm
//        return (this.title.hashCode() + this.authors.hashCode() +
//                this.ISBN.hashCode() - this.pages) * ((int)this.rating +1);
//    }

    @Override
    public String toString(){
        // Generate a specific layout
        return  getTitle() +
                "by "+ getAuthors()[0] +", " +
                getAuthors()[1] + "\n"+
                "Rating: " + getRating() + "\n" +
                "ISBN: " + getISBN() + "\n" +
                getPages() + " pages";
    }

    /**
     * This is helper function to detect potential exceptions
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
