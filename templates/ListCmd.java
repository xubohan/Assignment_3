import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {

    private String keySave;
    private final String longWord = "long";
    private final String shortWord = "short";
    private final String nullWord = null;

    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        List<BookEntry> bookDa =  data.getBookData();
        if (bookDa.size() == 0) {
            System.out.println("The library has no book entries.");
            return;
        }
        System.out.println(bookDa.size() + " books in library:");
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

    @Override
    protected boolean parseArguments(String argumentInput) {
        if (!shortWord.equals(argumentInput) || !longWord.equals(argumentInput) ||
                argumentInput != nullWord) {
            return false;
        } else {
            keySave = argumentInput;
            return true;
        }
    }


}
