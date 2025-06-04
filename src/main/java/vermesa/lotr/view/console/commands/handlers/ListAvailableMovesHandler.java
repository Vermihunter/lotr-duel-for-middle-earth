package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.game_events.GameEndedEvent;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListAvailableMovesHandler extends CommandHandler {
    private static final CommandResult CONSTANT_RESULT = new CommandResult(CommandResultType.CONTINUE, null, false);

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

    public static void removeFromParentHandler(ResourceBundle resourceBundle, ConsoleView console) {
        var commandName = resourceBundle.getString(CommandResourceBundleKeys.LIST_AVAILABLE_MOVES_NAME);
        console.getBaseCommandHandler().unregisterSubCommand(commandName);
    }

    private void printMoveGroup(List<IMove> moveGroup) {

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
    }

    private int chooseMoveInd(List<IMove> moveGroup) {
        while (true) {
            context.out.print(">> Move number to choose: ");
            int moveInd;
            if (context.scanner.hasNextInt()) {
                moveInd = context.scanner.nextInt();
                if (moveInd > moveGroup.size() || moveInd < 0) {
                    context.out.println(">>> Invalid move number - try again");
                    continue;
                }

                return moveInd - 1;
            } else {
                context.scanner.nextLine();      // <— skip the rest of the bad line
                context.out.println(
                        ">>> Invalid move number – try again"
                );
            }
        }
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) throws RemoteException {
        Player playerOnMove = game.state().getPlayerOnMove();
        Player humanPlayer = context.controller.getHumanPlayer();
        // Change it to equals
        if (!playerOnMove.getClass().equals(humanPlayer.getClass())) {
            context.out.println(">>> Its the other player's turn to move!");
            return new CommandResult(CommandResultType.CONTINUE, null, true);
        }


        var possibleMoves = context.controller.getPossibleMoves();
        var chosenMoves = new ArrayList<IAction>();

        // Print possible moves and let the user choose one from each group
        for (var moveGroup : possibleMoves) {
            context.out.println(">> Choose a move by its number:");
            printMoveGroup(moveGroup);

            int moveInd = chooseMoveInd(moveGroup);
            chosenMoves.add(moveGroup.get(moveInd));
        }

        var moveResult = context.controller.makeMove(chosenMoves);

        if (moveResult.currentGameState() != CurrentGameState.HAS_NOT_ENDED) {
            context.eventQueue.add(new GameEndedEvent(moveResult.currentGameState()));
            //context.notifyController();
        }

        if (!game.state().getPlayerOnMove().getClass().equals(context.controller.getHumanPlayer().getClass())) {
            context.out.println(">>> Enemy player is making a move...");
            //context.notifyController();
        }


        return CONSTANT_RESULT;
    }

}
