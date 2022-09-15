package util;

import java.util.Scanner;

/**
 * interface for working with commands with input via the console
 */
public interface CommandWithScanner{
    void execute(Scanner in);

    void execute(int id, Scanner in);
}
