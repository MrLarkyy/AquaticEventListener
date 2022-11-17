package xyz.larkyy.aquaticeventlistener;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AquaticEventListener {

    private static JavaPlugin plugin;
    private static EventRegistry eventRegistry;

    public static void init(JavaPlugin plugin) {
        if (AquaticEventListener.plugin != null) {
            return;
        }
        AquaticEventListener.plugin = plugin;
        eventRegistry = new EventRegistry();
        eventRegistry.register(PlayerJoinEvent.class,e -> {

        });
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static EventRegistry getEventRegistry() {
        return eventRegistry;
    }
}
