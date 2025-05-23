package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.LandmarkTileMove;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(LandmarkTileMove.class)
public class LandmarkTileMoveSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (LandmarkTileMove) _move;
        var moveSerializers = ActionSerializerRegistry.getAll();
        String actionsSerialized = move.landmarkTile().actions().stream()
                .map(a -> {
                    var serializer = moveSerializers.get(a.getClass());
                    return (serializer != null) ? serializer.serialize(a) : a.toString();
                })
                .collect(Collectors.joining("\t\t\t- ", "", ""));


        return "Landmark tile move placing fortress to " + move.landmarkTile().region().getRegionType()
                + "\n    \tWith actions: \n\t\t\t- "
                + actionsSerialized;
    }
}
