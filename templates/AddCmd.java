import java.nio.file.Path;
import java.util.Objects;

public class AddCmd extends LibraryCommand{

    private final String suffix = ".csv";
    private String argumentInput;


    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"Given input argument must not be null.");

        data.loadData(Path.of(argumentInput));

    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        if (argumentInput == null)  {
            try {
                throw new NullPointerException("Given input argument must not be null.");
            } catch (NullPointerException e){
                return false;
            }
        }

        if (argumentInput.length() < 5) {
            try {
                throw new StringIndexOutOfBoundsException("It is out of bound");
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }
        }

        if (!argumentInput.substring(argumentInput.length() - suffix.length()).equals(suffix)){
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
