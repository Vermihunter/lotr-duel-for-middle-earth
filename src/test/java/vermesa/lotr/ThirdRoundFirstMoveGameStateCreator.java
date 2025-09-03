package vermesa.lotr;


@TestedGameStateCreatorInfo(
        state = TestedGameState.THIRD_ROUND_FIRST_MOVE
)
public class ThirdRoundFirstMoveGameStateCreator extends RoundFirstMoveGameStateCreator {

    public ThirdRoundFirstMoveGameStateCreator() {
        super(3);
    }
}
