package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;

public class AllianceTokenSerializer {
    public static String serialize(AllianceToken allianceToken) {
        var allSerializers = ActionSerializerRegistry.getAll();
        if (allianceToken == null || allianceToken.action() == null) {
            throw new IllegalArgumentException("AllianceToken is null");
        }

        var serializer = allSerializers.get(allianceToken.action().getClass());

        if (serializer == null) {
            throw new IllegalArgumentException("No serializer registered for " + allianceToken.action().getClass().getName());
        }


        return serializer.serialize(allianceToken.action());
    }
}
