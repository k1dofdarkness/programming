package util;

/**
 * interface for working with commands without console input
 */
public interface Command {
    void execute();

    void execute(int id);

    void execute(String str);
}
