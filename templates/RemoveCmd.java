import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {
    private final String authorName = "AUTHOR";
    private final String titleName = "TITLE";

    private String sort;
    private String stringValue;

    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }


    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        Iterator<BookEntry> bookList = data.getBookData().iterator();
        if (sort.equals(authorName)) {
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
        if (sort.equals(titleName)) {
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
    }

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
            sort = authorName;
            stringValue = argumentInput.replaceFirst(authorName, "").trim();
            if (stringValue.isBlank()) {
                return false;
            }
            return true;
        } else if (titleCheck.equals(titleName)) {
            sort = titleName;
            stringValue = argumentInput.replaceFirst(titleName, "").trim();
            if (stringValue.isBlank()) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}

