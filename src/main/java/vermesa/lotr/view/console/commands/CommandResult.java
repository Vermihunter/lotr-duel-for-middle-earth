package vermesa.lotr.view.console.commands;

import vermesa.lotr.view.console.ConsoleView;

public record CommandResult(CommandResultType commandResult, String message, boolean printHelp, AppState nextView) {
    public static CommandResult OK(ConsoleView consoleView) {
        return new CommandResult(CommandResultType.CONTINUE, null, true, consoleView.getCurrentAppState());
    }
}
