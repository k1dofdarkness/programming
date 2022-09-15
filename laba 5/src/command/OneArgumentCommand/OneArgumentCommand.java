package command.OneArgumentCommand;

import util.Collection;
import util.Command;

/**
 * abstract class for commands with one argument
 */
public abstract class OneArgumentCommand implements Command {
    Collection collection;
    public OneArgumentCommand(Collection collection) {
        this.collection = collection;
    }
    public void execute(int id) {};

    public void execute() {
        System.out.println("Данная команда предполагает наличие аргумента.");
    };

    public void execute(String str) {};
}
