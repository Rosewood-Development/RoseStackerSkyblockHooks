package dev.esophose.skyblockhooks.provider;

import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

public class BentoBoxProvider implements SkyblockProvider {

    @Override
    public boolean canBreakAndPlace(Player player, Location location) {
        Optional<Island> optionalIsland = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        if (!optionalIsland.isPresent())
            return true;

        Island island = optionalIsland.get();
        User user = BentoBox.getInstance().getPlayersManager().getUser(player.getUniqueId());
        return island.isAllowed(user, Flags.BREAK_BLOCKS) && island.isAllowed(user, Flags.PLACE_BLOCKS);
    }

}
