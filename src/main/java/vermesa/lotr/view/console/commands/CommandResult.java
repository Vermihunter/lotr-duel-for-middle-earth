package vermesa.lotr.view.console.commands;

public record CommandResult(CommandResultType commandResult, String message, boolean printHelp) {
    public static final CommandResult OK = new CommandResult(CommandResultType.CONTINUE, null, true);
}
