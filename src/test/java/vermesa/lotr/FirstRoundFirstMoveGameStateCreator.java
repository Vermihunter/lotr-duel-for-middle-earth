package vermesa.lotr;

@TestedGameStateCreatorInfo(
        state = TestedGameState.FIRST_ROUND_FIRST_MOVE
)
public class FirstRoundFirstMoveGameStateCreator extends RoundFirstMoveGameStateCreator {
    public FirstRoundFirstMoveGameStateCreator() {
        super(1);
    }
}
