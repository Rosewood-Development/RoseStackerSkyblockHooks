package dev.rosewood.skyblockhooks.provider;

import com.iridium.iridiumskyblock.PermissionType;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumskyblock.managers.IslandManager;
import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class IridiumSkyblockProvider implements SkyblockProvider {

    @Override
    public boolean canBreakAndPlace(Player player, Location location) {
        Optional<Island> optionalIsland = IridiumSkyblockAPI.getInstance().getIslandViaLocation(location);
        if (!optionalIsland.isPresent())
            return true;

        Island island = optionalIsland.get();
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.BLOCK_BREAK)
                && IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.BLOCK_PLACE);
    }

}
