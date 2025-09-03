package vermesa.lotr;


@TestedGameStateCreatorInfo(
        state = TestedGameState.SECOND_ROUND_FIRST_MOVE
)
public class SecondRoundFirstMoveGameStateCreator extends RoundFirstMoveGameStateCreator {
    public SecondRoundFirstMoveGameStateCreator() {
        super(2);
    }
}
