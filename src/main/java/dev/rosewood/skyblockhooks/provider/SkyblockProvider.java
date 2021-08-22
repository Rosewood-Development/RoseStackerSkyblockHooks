package dev.rosewood.skyblockhooks.provider;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface SkyblockProvider {

    boolean canBreakAndPlace(Player player, Location location);

}
