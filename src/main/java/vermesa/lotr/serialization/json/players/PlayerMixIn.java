package vermesa.lotr.serialization.json.players;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.SauronPlayer;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SauronPlayer.class, name = "SauronPlayer"),
        @JsonSubTypes.Type(value = FellowshipPlayer.class, name = "FellowshipPlayer")
})
public abstract class PlayerMixIn {
    // no methods or fields—just annotations
}