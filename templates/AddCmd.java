import java.nio.file.Path;
import java.util.Objects;

public class AddCmd extends LibraryCommand{

    private final String suffix = ".csv";
    private String argumentInput;

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"Given input argument must not be null.");
        LibraryData ld = new LibraryData();
        ld.loadData(Path.of(argumentInput));
    }

    @Override
    protected boolean parseArguments(String argumentInput) {

        if (argumentInput == null)  {
            try {
                throw new NullPointerException("Given input argument must not be null.");
            }
            catch (NullPointerException e){
                e.printStackTrace();
                return false;
            }
        }

        if(!Path.of(argumentInput).toFile().exists()) {
            try {
                throw new NoSuchFieldException("Given input argument must not be a valid address.");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (!argumentInput.substring(argumentInput.length() - suffix.length()).equals(suffix)){
            throw new IllegalArgumentException("Invalid argument for ADD command");
        }

        this.argumentInput = argumentInput;
        return true;
    }
}
