package xyz.larkyy.aquaticeventlistener;

import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventListener<T extends Event> implements EventExecutor, Listener {

    private final List<EventAction<T>> actions;
    private final Class<T> eventClass;

    public EventListener(Class<T> eventClass){
        this.actions = new ArrayList<>();
        this.eventClass = eventClass;
    }

    public EventAction<T> addAction(Consumer<T> consumer) {
        var action = new EventAction<T>(consumer,this);
        actions.add(action);
        return action;
    }

    public void removeAction(EventAction<T> action) {
        actions.remove(action);
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
            actions.forEach(a -> {
                a.accept((T) event);
            });
        }
    }
}
