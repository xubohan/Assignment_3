import java.util.*;

public class GroupCmd extends LibraryCommand{
    /** authorName is fixed which is "AUTHOR" **/
    private final String authorName = "AUTHOR";

    /** titleName is fixed which is "TITLE" **/
    private final String titleName = "TITLE";

    /** a set of key words **/
    private ArrayList<String> key = new ArrayList<>();

    /** a set of value which is not corresponding to key **/
    private ArrayList<String> value = new ArrayList<>();

    /** a set of value which is special for "[0-9]" **/
    private ArrayList<BookEntry> setData = new ArrayList<>();

    /** a valid input, as conditions  **/
    private String dataSave;

    /**
     * create an group command
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");

        // if data is empty, finish this execution
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no book entries.");
            return;
        }

        System.out.format("Grouped data by %s\n",dataSave);
        Iterator<BookEntry> it = data.getBookData().iterator();

        switch (dataSave) {
            case titleName:
                titleData(it);
                break;
            case authorName:
                authorData(it);
                break;
            default:
                break;
        }
    }

    /**
     * including collecting sorting
     * and printing data
     * @param it a set of BookEntry data
     */
    private void titleData(Iterator<BookEntry> it) {
        ArrayList<String> numberBook = new ArrayList<>();
        String numb = null;
        numb = collectData(it, numberBook, numb);
        key = noDuplicate(key);
        Collections.sort(key);
        printingData(numberBook, numb);
    }

    /**
     * for printing data separately by letters and digits
     * @param numberBook contains book titles which are digits
     * @param numb can only be "[0-9]" or null
     */
    private void printingData(ArrayList<String> numberBook, String numb) {
        for (String key1 : key) {
            System.out.println("## " + key1);
            for (String value1 : value) {
                String firstLetter = String.valueOf(value1.charAt(0));
                if (firstLetter.equals(key1)) {
                    System.out.println("   " + value1);
                }
            }
        }
        if (numb != null) {
            System.out.println("## " + numb);
            for (String nb: numberBook) {
                System.out.println("   " + nb);
            }
        }
    }

    /**
     * for collecting data separately by letters and digits
     * @param it a set of BookEntry data
     * @param numberBook contains book titles which are digits
     * @param numb can only be "[0-9]" or null
     * @return if the book name contains digits, return "[0-9]"
     */
    private String collectData(Iterator<BookEntry> it, ArrayList<String> numberBook, String numb) {
        while (it.hasNext()) {
            String record = it.next().getTitle();
            char firstLetter = record.charAt(0);
            if (Character.isLetter(firstLetter)) {
                key.add(String.valueOf(firstLetter).toUpperCase());
                value.add(record);
            } else if (Character.isDigit(firstLetter)) {
                numb = "[0-9]";
                numberBook.add(record);
            }
        }
        return numb;
    }

    /**
     * this helper function is for
     * sorting and printing data
     * @param it a set of BookEntry data
     */
    private void authorData(Iterator<BookEntry> it) {
        collectAndSortAuthorData(it);
        for (String key1: key) {
            System.out.println("## " + key1);
            for (BookEntry setData1 : setData) {
                if (Arrays.asList(setData1.getAuthors()).contains(key1)) {
                    value.add(setData1.getTitle());
                }
            }
            Collections.sort(value);
            for (String value1: value) {
                System.out.println("   " + value1);
            }
            value.clear();
        }
    }

    /**
     * this helper function is for collecting
     * and sorting data
     * @param it a set of BookEntry data
     */
    private void collectAndSortAuthorData(Iterator<BookEntry> it) {
        while (it.hasNext()) {
            BookEntry dataRec = it.next();
            String[] name = dataRec.getAuthors();
            setData.add(dataRec);
            for (String nam1 : name) {
                key.add(nam1);
            }
        }
        key = noDuplicate(key);
        Collections.sort(key);
    }

    /**
     * parseArguments for checking format
     * @param argumentInput argument input for this command
     * @return false if cannot meet requirements
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if (argumentInput.equals(authorName)
                || argumentInput.equals(titleName)) {
            dataSave = argumentInput;
            return true;
        }
        return false;
    }

    /**
     * a method for convenience
     * @param arr Give an ArrayList<String>
     * @return an ArrayList<String> which has no duplicate letters
     */
    private ArrayList<String> noDuplicate(ArrayList<String> arr) {
        Iterator<String> iterator = arr.iterator();
        ArrayList<String> simple = new ArrayList<>();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (!simple.contains(temp)) {
                simple.add(temp);
            }
        }
        // using library functions to quickly sort
        Collections.sort(simple);
        return simple;
     }

}
