package vermesa.lotr.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import vermesa.lotr.model.central_board.RegionType;

public abstract class RegionMixIn {
    @JsonCreator
    public RegionMixIn(
            @JsonProperty("regionType") RegionType regionType,
            @JsonProperty("fortressName") String fortressName
    ) {
        // no body needed—Jackson only uses the signature.
    }
}
