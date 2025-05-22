package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class ListAvailableMovesHandler extends CommandHandler {
    private final Game game;

    public ListAvailableMovesHandler(Context context, String name, String description, Game game) {
        super(context, name, description);
        this.game = game;
    }

    public static void addAsSubHandler(ResourceBundle resourceBundle, Game game, ConsoleView console, Context context) {
        var commandName = resourceBundle.getString(CommandResourceBundleKeys.LIST_AVAILABLE_MOVES_NAME);
        var commandDescription = resourceBundle.getString(CommandResourceBundleKeys.LIST_AVAILABLE_MOVES_HELP_MESSAGE);
        console.getBaseCommandHandler().registerSubCommand(commandName, new ListAvailableMovesHandler(context, commandName, commandDescription, game));
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        var possibleMoves = game.getPossibleMoves();
        var chosenMoves = new ArrayList<IAction>();

        for (var moveGroup : possibleMoves) {
            context.out.println(">> Choose a move by its number:");

            int index = 1;
            for (var move : moveGroup) {
                var moveSerializer = ActionSerializerRegistry.getAll().get(move.getClass());

                String moveSerialized = move.toString();
                if (moveSerializer != null) {
                    moveSerialized = moveSerializer.serialize(move);
                }


                context.out.print(">> \t(" + index + ") - " + moveSerialized);
                ++index;
            }

            while (true) {
                context.out.print(">> Move number to choose: ");
                int moveInd;
                if (context.scanner.hasNextInt()) {
                    moveInd = context.scanner.nextInt();
                    if (moveInd > moveGroup.size() || moveInd < 0) {
                        context.out.println(">>> Invalid move number - try again");
                        continue;
                    }

                    chosenMoves.add(moveGroup.get(moveInd - 1));
                    break;
                } else {
                    context.scanner.nextLine();      // <— skip the rest of the bad line
                    context.out.println(
                            ">>> Invalid move number – try again"
                    );
                }


            }
        }

        game.makeMove(chosenMoves);
        synchronized (context.controllerLock) {
            context.controllerLock.notify();
        }

        return new CommandResult(CommandResultType.CONTINUE, null);
    }

}
