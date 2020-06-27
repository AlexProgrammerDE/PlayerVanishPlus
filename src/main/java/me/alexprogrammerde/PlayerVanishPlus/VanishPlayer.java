package me.alexprogrammerde.PlayerVanishPlus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VanishPlayer {
    public static void vanishPlayer(Player vanishplayer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != vanishplayer) {
                player.hidePlayer(Main.getPlugin(Main.class), vanishplayer);
            }
        }
    }

    public static void unvanishPlayer(Player unvanishplayer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != unvanishplayer) {
                player.showPlayer(Main.getPlugin(Main.class), unvanishplayer);
            }
        }
    }
}