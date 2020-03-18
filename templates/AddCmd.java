import java.nio.file.Path;
import java.util.Objects;

public class AddCmd extends LibraryCommand{

    /** the suffix of file name **/
    private final String suffix = ".csv";

    /** this is for saving valid value **/
    private String argumentInput;

    /**
     * create an add command
     * @param argumentInput argument input is expected to be blank
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    /**
     * execute the add command
     * this is for adding data from files
     * @param data book data to be considered for command execution
     * @throws NullPointerException data may be null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"Given input argument must not be null.");

        // simple call of the given LibraryData instance’s loadData method with the path
        data.loadData(Path.of(argumentInput));
    }

    /**
     * parseArguments for checking format
     * @param argumentInput argument input for this command
     * @throws NullPointerException argumentInput may be null
     * @throws IllegalArgumentException argumentInput may not have valid suffix
     * @return depends on the format whether is correct
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if (argumentInput == null)  {
            try {
                throw new NullPointerException("Given input argument must not be null.");
            } catch (NullPointerException e){
                return false;
            }
        }

        if (argumentInput.length() < suffix.length() + 1 ||
                !argumentInput.substring(argumentInput.length() - suffix.length()).equals(suffix)) {
            try {
                throw new IllegalArgumentException("Invalid argument for ADD command");
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        this.argumentInput = argumentInput;
        return true;
    }
}
