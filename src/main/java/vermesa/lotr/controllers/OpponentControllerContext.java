package vermesa.lotr.controllers;

import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;

public record OpponentControllerContext(Game game, Player humanPlayer, Player opponentPlayer) implements Serializable {
}
