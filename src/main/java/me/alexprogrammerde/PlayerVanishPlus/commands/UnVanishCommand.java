package me.alexprogrammerde.PlayerVanishPlus.commands;

import me.alexprogrammerde.PlayerVanishPlus.Main;
import me.alexprogrammerde.PlayerVanishPlus.VanishPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnVanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("playervanishplus.unvanish")) {
                VanishPlayer.unvanishPlayer(player);

                if (Main.getPlugin(Main.class).isVanished(player)) {
                    player.setGameMode(Main.getPlugin(Main.class).getGamemode(player));
                    Main.getPlugin(Main.class).removePlayer(player);

                    player.sendMessage("You are now unvanished!");
                } else {
                    sender.sendMessage("You are not vanished!");
                }
            } else {
                sender.sendMessage("You have no permission to do that!");
            }
        } else {
            sender.sendMessage("You need to be online to do that!");
        }

        return false;
    }
}
