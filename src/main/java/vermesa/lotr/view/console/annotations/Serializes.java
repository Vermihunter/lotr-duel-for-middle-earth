package vermesa.lotr.view.console.annotations;

import vermesa.lotr.model.actions.IAction;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Serializes {
    /**
     * The IAction implementation class that this serializer handles.
     */
    Class<? extends IAction> value();
}

