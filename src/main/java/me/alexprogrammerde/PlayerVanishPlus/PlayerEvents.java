package me.alexprogrammerde.PlayerVanishPlus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player vanishedplayer : Main.getPlugin(Main.class).getGamemodeList().keySet()) {
            player.hidePlayer(Main.getPlugin(Main.class), vanishedplayer);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (Main.getPlugin(Main.class).isVanished(player) && Main.getPlugin(Main.class).getFileConfig().getBoolean("unvanish-on-leave")) {
            Main.getPlugin(Main.class).removePlayer(player);
        }
    }
}
