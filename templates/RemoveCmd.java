import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {

    /** authorName is fixed which is "AUTHOR" **/
    private final String authorName = "AUTHOR";

    /** titleName is fixed which is "TITLE" **/
    private final String titleName = "TITLE";

    /** saving the valid condition **/
    private String sort;

    /** saving the valid path **/
    private String stringValue;

    /**
     * create an remove command
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }

    /**
     * execute the remove command
     * for removing specific books in the book list
     * @param data book data to be considered for command execution
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        Iterator<BookEntry> bookList = data.getBookData().iterator();
        if (sort.equals(authorName)) {
            removeAuthor(bookList);
        }
        if (sort.equals(titleName)) {
            removeTitle(data, bookList);
        }
    }

    /**
     * removing all author-related books
     * @param bookList currently loaded book list
     */
    private void removeAuthor(Iterator<BookEntry> bookList) {
        int count = 0;
        while (bookList.hasNext()) {
            String[] record = bookList.next().getAuthors();
                if (Arrays.asList(record).contains(stringValue)) {
                    bookList.remove();
                    count++;
                }
        }
        System.out.format("%d books removed for author: %s\n", count, stringValue);
    }

    /**
     * removing all title-related books
     * @param data currently loaded book data
     * @param bookList currently loaded book list
     */
    private void removeTitle(LibraryData data, Iterator<BookEntry> bookList) {
        int rec = data.getBookData().size();
        while (bookList.hasNext()) {
            String tempSave = bookList.next().getTitle();
            if (tempSave.equals(stringValue)) {
                bookList.remove();
                System.out.format("%s: removed successfully.\n", stringValue);
            }
        }
        if (rec == data.getBookData().size()) {
            System.out.format("%s: not found.\n", stringValue);
        }
    }

    /**
     * parseArguments for checking format
     * @param argumentInput argument input for this command
     * @throws NullPointerException argumentInput may be null
     * @return depends on the format whether is correct
     */
    @Override
    protected boolean parseArguments(String argumentInput) {

        if (argumentInput.isEmpty()) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                return false;
            }
        }

        String authorCheck = argumentInput.substring(0,authorName.length());
        String titleCheck = argumentInput.substring(0,titleName.length());

        if (authorCheck.equals(authorName)) {
            if (checkData(argumentInput, authorName)) return false;
            return true;
        } else if (titleCheck.equals(titleName)) {
            if (checkData(argumentInput, titleName)) return false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * checking data and saving valid value
     * @param argumentInput argument input is not be blank, we check before
     * @param titleName key word, used to replace data
     * @return if it meets all conditions, return true
     */
    private boolean checkData(String argumentInput, String titleName) {
        sort = titleName;
        stringValue = argumentInput.replaceFirst(sort, "").trim();
        if (stringValue.isBlank()) {
            return true;
        }
        return false;
    }
}

