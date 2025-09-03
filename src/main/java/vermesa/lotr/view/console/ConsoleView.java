package vermesa.lotr.view.console;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.view.console.commands.*;
import vermesa.lotr.view.console.commands.exceptions.BadCommandArgumentsException;
import vermesa.lotr.view.console.commands.exceptions.CommandNotFoundException;
import vermesa.lotr.view.console.commands.handlers.CommandHandler;
import vermesa.lotr.view.console.utils.BoxPrinter;
import vermesa.lotr.view.console.utils.BoxPrinter.Section;

import java.rmi.RemoteException;

public class ConsoleView {
    private final CommandViewRegistry commandViewRegistry;
    //private final CommandHandler baseCommandHandler;
    private final Context context;
    private CommandView currentHandler;
    private AppState currentAppState;

    public ConsoleView(CommandViewRegistry commandViewRegistry, Context context, AppState currentAppState) {
        this.commandViewRegistry = commandViewRegistry;
        //  this.baseCommandHandler = baseCommandHandler;
        this.context = context;
        this.currentAppState = currentAppState;
        this.currentHandler = commandViewRegistry.getCommandView(currentAppState);
    }

    public Context getContext() {
        return context;
    }

    public void switchToView(AppState appState) {
        currentHandler = commandViewRegistry.getCommandView(appState);
        currentAppState = appState;
    }

    public AppState getCurrentAppState() {
        return currentAppState;
    }

    public CommandHandler getBaseCommandHandler() {
        return currentHandler.getCommandHandler();
    }

    public CommandResult execute(String[] command) {
        return handleCommand(command);
    }

    private CommandResult handleCommand(String[] command) {
        CommandResult result;

        try {
            result = currentHandler.handle(command, this);
        } catch (Exception e) {
            result = new CommandResult(CommandResultType.ERROR, e.getMessage(), true, currentAppState);
        }

        return result;
    }

    public void printWelcome() {
        int boxWidth = 80;

        Section titleSection = new Section(
                "The Lord of the Rings: Duel for Middle-earth"
        );

        Section welcomeSection = new Section(
                "One box to rule them all... sharpen your wits!",
                "May your fellowship be unbreakable and your RNG merciful!",
                "Tread carefully, for every orc has a pointy spear—and a bad attitude!",
                "Let the battle for Middle-earth BEGIN!"
        );

        BoxPrinter.printSections(
                new Section[]{titleSection, welcomeSection},
                boxWidth, context
        );
    }

    public void printHelp() throws RemoteException {
        //baseCommandHandler.handleCommand(new String[]{context.resourceBundle.getString(CommandResourceBundleKeys.LIST_NAME)}, this);
        currentHandler.printHelp();
    }
}
