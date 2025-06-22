package vermesa.lotr;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestedGameStateCreatorInfo {
    TestedGameState state();
}
