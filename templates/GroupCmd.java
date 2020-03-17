import java.util.*;

public class GroupCmd extends LibraryCommand{
    private String authorName = "AUTHOR";
    private String titleName = "TITLE";
    private String dataSave;

    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given input argument must not be null.");
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no book entries.");
        }

        System.out.format("Grouped data by %s\n",dataSave);
        Iterator<BookEntry> it = data.getBookData().iterator();
        if (dataSave.equals(titleName)) {
            HashMap<String, String> hm1 = new HashMap<>();
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> valueRecord = new ArrayList<>();
            while (it.hasNext()) {
                String tempTitle = it.next().getTitle();
                char letter = tempTitle.charAt(0);
                if (Character.isAlphabetic(letter)) {
                    hm1.put(tempTitle, String.valueOf(letter).toUpperCase());
                    valueRecord.add(String.valueOf(letter).toUpperCase());
                }
                if (Character.isDigit(letter)) {
                    arrayList.add(tempTitle);
                }
                    //最后一个如果不是 数字或则字母
                    //写完再处理
            }
            printValue(hm1, arrayList, valueRecord);

        }
        if (dataSave.equals(authorName)) {
            ArrayList<String> name = new ArrayList<>();
            while (it.hasNext()) {
                for (String authorName1 :it.next().getAuthors()) {
                    name.add(authorName1);
                }
            }
            name = noDuplicate(name);
            for (String name1 : name) {
                System.out.format("## %s\n",name1);
                ArrayList<String> temperSave = new ArrayList<>();
                while (it.hasNext()) {
                    if (Arrays.asList(it.next().getAuthors()).contains(name1)) {
                        temperSave.add(it.next().getTitle());
                    }
                }
                Collections.sort(temperSave);
                for (String tempUse:temperSave){
                    System.out.format("    %s\n",tempUse);
                }
            }
        }
    }

    private void printValue(HashMap<String, String> hm1, ArrayList<String> arrayList, ArrayList<String> valueRecord) {
        for (String value : noDuplicate(valueRecord)) {
            System.out.format("## %s\n",value);
            ArrayList<String> record1 = new ArrayList<>();
            for (String key: hm1.keySet()) {
                if (value.equals(hm1.get(key))) {
                    record1.add(key);
                }
            }
            Collections.sort(record1);
            for (String record2 : record1) {
                System.out.format("    %s\n",record2);
            }
        }
        Collections.sort(arrayList);

        System.out.println("## [0-9]");
        for (String temp : arrayList) {
            System.out.format("    %s\n",temp);
        }
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        boolean check = false;
        if (argumentInput.equals(authorName)
                || argumentInput.equals(titleName)) {
            dataSave = argumentInput;
            check = true;
        }
        return check;
    }

    private ArrayList<String> noDuplicate(ArrayList<String> arr) {
        Iterator<String> iterator = arr.iterator();
        ArrayList<String> simple = new ArrayList<>();
        while (iterator.hasNext()) {
            if (!simple.contains(iterator.next())) {
                simple.add(iterator.next());
            }
        }
        Collections.sort(simple);
        return simple;
     }
}
