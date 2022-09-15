package command.ScannerArgumentCommand;

import util.Collection;
import util.CommandWithScanner;

import java.util.Scanner;

/**
 * abstract class for commands with console input
 */
public abstract class ScannerCommand  implements CommandWithScanner {
    Collection collection;
    Scanner scanner;
    public ScannerCommand(Collection collection, Scanner scanner) {
        this.collection = collection;
        this.scanner = scanner;
    }
    public void execute(Scanner in) {}

    public void execute(int id, Scanner in) {}


}
