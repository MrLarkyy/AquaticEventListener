package xyz.larkyy.aquaticeventlistener;

import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;

import java.util.function.Consumer;

public class EventListener<T extends Event> implements EventExecutor, Listener {

    private Consumer<T> consumer;
    private final Class<T> eventClass;

    public EventListener(Class<T> eventClass, Consumer<T> consumer){
        this.eventClass = eventClass;
        this.consumer = consumer;
    }

    public void addAction(Consumer<T> consumer) {
        this.consumer = this.consumer.andThen(consumer);
    }

    public void register() {
        var plugin = AquaticEventListener.getPlugin();

        plugin.getServer().getPluginManager().registerEvent(eventClass, this, EventPriority.NORMAL,this,plugin,false);
        EventRegistry.get().registerListener(this);
    }

    public Class<T> getEventClass() {
        return eventClass;
    }

    @Override
    public void execute(Listener listener, Event event) throws EventException {
        if (getEventClass().isInstance(event)) {
            consumer.accept((T) event);
        }
    }
}
