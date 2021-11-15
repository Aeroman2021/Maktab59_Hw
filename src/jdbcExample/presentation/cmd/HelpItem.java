package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;

public class HelpItem implements MenuCommand {

    @Override
    public int select() {
        return 1;
    }

    @Override
    public void execute() {

        System.out.println("Please select a number between 1 to 8");

    }

}
