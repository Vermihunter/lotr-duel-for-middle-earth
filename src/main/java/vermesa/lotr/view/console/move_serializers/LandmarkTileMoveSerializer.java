package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.LandmarkTileMove;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(LandmarkTileMove.class)
public class LandmarkTileMoveSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (LandmarkTileMove) _move;
        return "Landmark tile move → " + move.landmarkTile().region().getRegionType() + "\n";
    }
}
