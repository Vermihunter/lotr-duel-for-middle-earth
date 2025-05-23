package vermesa.lotr.view.console.move_serializers;

import org.reflections.Reflections;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActionSerializerRegistry {
    private static final Map<Class<? extends IAction>, IActionSerializer> serializers = new HashMap<>();

    static {
        // scan the classpath under basePackage for @Serializes
        Reflections reflections = new Reflections("vermesa.lotr.view.console.move_serializers");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Serializes.class);

        for (Class<?> clazz : annotated) {
            // type‐safety: we know clazz implements IActionSerializer
            IActionSerializer serializer = null;
            try {
                serializer = (IActionSerializer) clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ignored) {
                // Ignore
            }

            Class<? extends IAction> actionType = clazz.getAnnotation(Serializes.class).value();
            serializers.put(actionType, serializer);
        }
    }

    public static Map<Class<? extends IAction>, IActionSerializer> getAll() {
        return serializers;
    }

    public static String serialize(IAction action) {
        return serializers.get(action.getClass()).serialize(action);
    }
}
