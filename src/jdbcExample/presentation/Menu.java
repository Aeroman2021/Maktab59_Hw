package jdbcExample.presentation;

import jdbcExample.UI.Input;
import jdbcExample.exception.InvalidDataException;
import jdbcExample.presentation.cmd.*;

import java.util.List;

public class Menu {

    private final MenuCommandExecutor menuCommandExecutor;

    public Menu() {
        this.menuCommandExecutor = new MenuCommandExecutor(initialCommand());
    }

    public List<MenuCommand> initialCommand() {
        return List.of(new AddItem(), new DeleteItem(), new LoadItemById(),
                new LoadAllItems());
    }

    public void startApplication() {
        boolean showMenu = true;
        while (showMenu) {
            menu();
            showMenu = chooseMenu();
        }

    }

    public boolean chooseMenu() {
        int selected = Input.getInt();
        if (selected >= 8)
            return false;
        menuCommandExecutor.execute(selected);
        return true;
    }

    private void menu() {
        System.out.println("""
                                  
                Main Menu - Select an action:
                1. Help
                2. Add
                3. Update
                4. Delete
                5. Load Item by Id
                6. Load all items
                7. Exit
                                                  
                        """);
    }
}
