import java.util.*;

public class SearchCmd extends LibraryCommand{

    /** saving valid data **/
    private String validCollect;

    /**
     * create an search command
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

    /**
     * execute the search command
     * @param data book data to be considered for command execution
     * @throws NullPointerException if the given argumentInput is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        Iterator<BookEntry> it = data.getBookData().iterator();
        ArrayList<String>record = new ArrayList<>();

        titleContainKey(it, record);
        printData(record);
    }

    /**
     * printing data set or
     * showing no such file
     * @param record a satisfied data set
     */
    private void printData(ArrayList<String> record) {
        if (record.size() == 0) {
            System.out.println("No hits found for search term: " + validCollect);
        } else {
            for (String temp : record) {
                System.out.println(temp);
            }
        }
    }

    /**
     * this is for recording valid data
     * @param it a set of data in BookEntry type
     * @param record saving data which satisfying conditions
     */
    private void titleContainKey(Iterator<BookEntry> it, ArrayList<String> record) {
        while (it.hasNext()) {
            String titleName = it.next().getTitle();
            if (titleName.toUpperCase().contains(validCollect.toUpperCase())) {
                record.add(titleName);
            }
        }
    }

    /**
     * parseArguments for checking format
     * @param argumentInput argument input for this command
     * @throws IllegalArgumentException if cannot meet the requirements
     * @return true if argumentInput meets conditions
     */
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
