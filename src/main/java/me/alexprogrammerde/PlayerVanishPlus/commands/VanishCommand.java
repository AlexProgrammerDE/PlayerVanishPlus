package me.alexprogrammerde.PlayerVanishPlus.commands;

import me.alexprogrammerde.PlayerVanishPlus.Main;
import me.alexprogrammerde.PlayerVanishPlus.VanishPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    FileConfiguration config;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        config = Main.getPlugin(Main.class).getFileConfig();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Main.getPlugin(Main.class).isVanished(player)) {
                if (player.hasPermission("playervanishplus.unvanish")) {
                    VanishPlayer.unvanishPlayer(player);

                    if (Main.getPlugin(Main.class).isVanished(player)) {
                        player.setGameMode(Main.getPlugin(Main.class).getGamemode(player));
                        Main.getPlugin(Main.class).removePlayer(player);

                        Bukkit.broadcastMessage(config.getString("joinmessage").replaceAll("%playername%", player.getDisplayName()));

                        player.sendMessage("You are now unvanished!");
                    } else {
                        sender.sendMessage("You are not vanished!");
                    }
                } else {
                    sender.sendMessage("You have no permission to do that!");
                }
            } else {
                if (player.hasPermission("playervanishplus.vanish")) {
                    GameMode mode = player.getGameMode();

                    Main.getPlugin(Main.class).putGamemode(player, mode);

                    VanishPlayer.vanishPlayer(player);
                    player.setGameMode(GameMode.CREATIVE);

                    Main.getPlugin(Main.class).getLogger().info(Main.getPlugin(Main.class).getGamemodeList().toString());

                    Bukkit.broadcastMessage(config.getString("quitmessage").replaceAll("%playername%", player.getDisplayName()));

                    player.sendMessage("You are now vanished!");
                } else {
                    sender.sendMessage("You have no permission to do that!");
                }
            }
        } else {
            sender.sendMessage("You need to be online to do that!");
        }

        return false;
    }
}
