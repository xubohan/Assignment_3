import java.security.Key;
import java.util.*;

public class GroupCmd extends LibraryCommand{
    private final String authorName = "AUTHOR";
    private final String titleName = "TITLE";
    private ArrayList<String> key = new ArrayList<>();
    private ArrayList<String> value = new ArrayList<>();
    private ArrayList<BookEntry> setData = new ArrayList<>();

    private String dataSave;

    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no book entries.");
            return;
        }

        System.out.format("Grouped data by %s\n",dataSave);
        Iterator<BookEntry> it = data.getBookData().iterator();

        switch (dataSave) {
            case titleName:
                ArrayList<String> numberBook = new ArrayList<>();
                String numb = null;
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
                key = noDuplicate(key);
                Collections.sort(key);
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
                break;
            case authorName:
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
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        if (argumentInput.equals(authorName)
                || argumentInput.equals(titleName)) {
            dataSave = argumentInput;
            return true;
        }
        return false;
    }

    private ArrayList<String> noDuplicate(ArrayList<String> arr) {
        Iterator<String> iterator = arr.iterator();
        ArrayList<String> simple = new ArrayList<>();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (!simple.contains(temp)) {
                simple.add(temp);
            }
        }
        Collections.sort(simple);
        return simple;
     }

}
