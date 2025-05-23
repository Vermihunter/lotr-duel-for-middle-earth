package vermesa.lotr.view.console.annotations;

import vermesa.lotr.model.actions.IAction;

import java.lang.annotation.*;

/**
 * Annotation that is used for collecting the serializers using reflection
 * in the {@link vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry} singleton cache
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Serializes {
    /**
     * The IAction implementation class that this serializer handles.
     */
    Class<? extends IAction> value();
}

