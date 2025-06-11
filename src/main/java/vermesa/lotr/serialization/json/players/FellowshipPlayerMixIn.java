package vermesa.lotr.serialization.json.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


// Tells Jackson: “When deserializing into FellowshipPlayer directly, ignore the 'type' rule.”
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public abstract class FellowshipPlayerMixIn {
    @JsonCreator
    public FellowshipPlayerMixIn(
            @JsonProperty("startingCoins") int startingCoins,
            @JsonProperty("units") int units,
            @JsonProperty("towers") int towers
    ) { /* no body */ }
}