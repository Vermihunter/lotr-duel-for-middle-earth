package vermesa.lotr.model;

public interface IAction {
    public ActionResult action(GameContext ctx, GameState state);
}
