package jdbcExample.presentation.cmd;

public class ExitItem implements MenuCommand{

    @Override
    public int select() {
        return 7;
    }

    @Override
    public void execute() {
        System.out.println("Exiting the application");
        System.exit(0);

    }
}
