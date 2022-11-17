package xyz.larkyy.aquaticeventlistener;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventRegistry {

    private final Map<Class<? extends Event>,EventListener> listeners = new HashMap<>();

    public <T extends Event> void register(Class<T> clazz, Consumer<T> consumer) {
        EventListener aquaticListener = listeners.get(clazz);
        if (aquaticListener != null) {
            addActionToRegistered(aquaticListener,consumer);
        }
        else {
            aquaticListener = new EventListener<>(clazz,consumer);
            aquaticListener.register();
        }
    }

    public void registerListener(EventListener<? extends Event> listener) {
        listeners.put(listener.getEventClass(),listener);
    }

    private <T extends Event> void addActionToRegistered(EventListener<T> listener, Consumer<T> consumer) {
        listener.addAction(consumer);
    }

    public static EventRegistry get() {
        return AquaticEventListener.getEventRegistry();
    }
}
