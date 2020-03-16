import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand{
    private String authorName = "AUTHOR";
    private String titleName = "TITLE";

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
                    if (Arrays.asList(bookList.next().getAuthors()).contains(stringValue)) {
                        bookList.remove();
                        count++;
                    }
            }
            System.out.format("%d books removed for author: <remove value>\n", count);
        }
        if (sort.equals(titleName)) {
            while (bookList.hasNext()) {
                if (bookList.next().getTitle().equals(stringValue)) {
                    System.out.format("%s: removed successfully.\n", stringValue);
                }

                if (!bookList.hasNext()) {
                    System.out.format("%s: not found.\n", stringValue);
                }
            }
        }
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        boolean check = false;
        if (argumentInput == null) {
            check = false;
        }
        String[] dete = argumentInput.split(" ");
        if (dete[0].contains(authorName)) {
            sort = authorName;
            stringValue = argumentInput.replaceFirst(authorName, "").trim();
            check = true;
        }

        if (dete[0].contains(titleName)) {
            sort = titleName;
            stringValue = argumentInput.replaceFirst(titleName, "").trim();
            check = true;
        }
        return check;
    }
}
