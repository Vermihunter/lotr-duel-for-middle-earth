package vermesa.lotr.serialization.json.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// By specifying Id.NONE here, we tell Jackson:
//   “When you know the target class is SauronPlayer, do not require any 'type' field.”
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public abstract class SauronPlayerMixIn {
    @JsonCreator
    public SauronPlayerMixIn(
            @JsonProperty("startingCoins") int startingCoins,
            @JsonProperty("units") int units,
            @JsonProperty("towers") int towers
    ) { /* no body—Jackson only uses the signature */ }
}