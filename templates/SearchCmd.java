import java.util.*;

public class SearchCmd extends LibraryCommand{

    private String validCollect;

    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        Iterator<BookEntry> it = data.getBookData().iterator();

        while (it.hasNext()) {
            if (it.next().getTitle().toUpperCase().contains(validCollect.toUpperCase())) {
                System.out.println(it.next());
            }

            if (!it.hasNext()) {
                System.out.format("No hits found for search term: %s", validCollect);
            }
        }
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        String space = " ";
        String[] detect = argumentInput.split(space);
        if (detect.length != 1 || space.equals(argumentInput)) {
            return false;
        }
        validCollect = argumentInput;
        return true;
    }
}
