package jdbcExample.presentation.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCommandExecutor {
    private final Map<Integer, MenuCommand> commandMap;

    public MenuCommandExecutor(List<MenuCommand> commands) {
        Map<Integer, MenuCommand> commandMap = new HashMap<>();
        for (MenuCommand command : commands)
            commandMap.put(command.select(), command);

        this.commandMap = commandMap;
    }

    public void execute(Integer selected){
        commandMap.get(selected).execute();
    }
}
