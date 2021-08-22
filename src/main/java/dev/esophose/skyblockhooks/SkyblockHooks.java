package dev.esophose.skyblockhooks;

import dev.esophose.skyblockhooks.provider.BentoBoxProvider;
import dev.esophose.skyblockhooks.provider.SkyblockProvider;
import dev.rosewood.rosestacker.event.StackGUIOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyblockHooks extends JavaPlugin implements Listener {

    private SkyblockProvider provider;

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTask(this, () -> {
            if (!Bukkit.getPluginManager().isPluginEnabled("RoseStacker")) {
                this.getLogger().severe("RoseStacker is not installed meaning this plugin is useless, disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }

            if (Bukkit.getPluginManager().isPluginEnabled("BentoBox")) {
                this.provider = new BentoBoxProvider();
            }

            if (this.provider == null) {
                this.getLogger().severe("A supported skyblock plugin is not installed meaning this plugin is useless, disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }

            Bukkit.getPluginManager().registerEvents(this, this);
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onStackGuiOpen(StackGUIOpenEvent event) {
        if (this.provider != null && !this.provider.canBreakAndPlace(event.getPlayer(), event.getStack().getLocation()))
            event.setCancelled(true);
    }

}
