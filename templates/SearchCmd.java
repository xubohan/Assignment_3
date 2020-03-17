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
        ArrayList<String>record = new ArrayList<>();

        while (it.hasNext()) {
            String titleName = it.next().getTitle();
            if (titleName.toUpperCase().contains(validCollect.toUpperCase())) {
                record.add(titleName);
            }
        }

        if (record.size() == 0) {
            System.out.println("No hits found for search term: " + validCollect);
        } else {
            for (String temp : record) {
                System.out.println(temp);
            }
        }
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        String space = " ";
        String[] detect = argumentInput.split(space);
        if (detect.length != 1 || argumentInput.isBlank()) {
            try {
                throw new IllegalArgumentException("This is invalid input.");
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        validCollect = argumentInput;
        return true;
    }
}
