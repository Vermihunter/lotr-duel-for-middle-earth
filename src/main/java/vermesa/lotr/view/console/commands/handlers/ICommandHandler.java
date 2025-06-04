package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.ConsoleView;

import java.rmi.RemoteException;

/**
 * Represents a command handler that is used to process the input from the user
 * using the console view
 */
public interface ICommandHandler {

    /**
     * Registers a command a sub-command with a given name
     *
     * @param subCommandName The name of the subcommand
     * @param handler        The handler that will be called to invoke the sub-command
     */
    void registerSubCommand(String subCommandName, ICommandHandler handler);

    /**
     * Unregisters a sub-command by name
     * This is useful in situations where we would like to limit what commands can the user
     * use at a given moment e.g. when the enemy player moves, the human player should not
     * call for a {@link ListAvailableMovesHandler}
     *
     * @param subCommandName Name of the sub-command to unregister
     */
    void unregisterSubCommand(String subCommandName);

    /**
     * Implements the logic of handling the command
     * @param commandParts The command parts to process - passes the unprocessed ones for the next sub-command
     * @param console Context to print in
     * @return Result of the command
     */
    CommandResult handleCommand(String[] commandParts, ConsoleView console) throws RemoteException;

    /** Helpers */
    String getDescription();
    String getName();
}
