import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {

    /** a valid value for further use **/
    private String keySave;

    /** a fixed String value, long, also key word **/
    private final String longWord = "long";

    /** a fixed String value, short, also key word**/
    private final String shortWord = "short";

    /**
     * create an list command
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    /**
     * execute the add command
     * this is for showing data
     * @param data book data to be considered for command execution
     * @throws NullPointerException if data is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        List<BookEntry> bookDa =  data.getBookData();
        if (bookDa.size() == 0) {
            System.out.println("The library has no book entries.");
            return;
        }
        System.out.println(bookDa.size() + " books in library:");
        printData(bookDa);
    }

    /**
     * this method is for printing data
     * @param bookDa a list of BookEntry data
     */
    private void printData(List<BookEntry> bookDa) {
        Iterator<BookEntry> it = bookDa.iterator();
        if (longWord.equals(keySave)) {
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }
        } else {
            while (it.hasNext()) {
                System.out.println(it.next().getTitle());
            }
        }
    }

    /**
     * checking whether argumentInput is correct
     * @param argumentInput argument input for this command
     * @return if it is correct, it is true; otherwise false
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if (shortWord.equals(argumentInput) || argumentInput.equals(longWord)
            || argumentInput.isBlank()) {
            keySave = argumentInput;
            return true;
        }
            return false;
    }
}
