package me.alexprogrammerde.PlayerVanishPlus.commands;

import me.alexprogrammerde.PlayerVanishPlus.Main;
import me.alexprogrammerde.PlayerVanishPlus.VanishPlayer;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("playervanishplus.vanish")) {
                GameMode mode = player.getGameMode();

                Main.getPlugin(Main.class).putGamemode(player, mode);

                VanishPlayer.vanishPlayer(player);
                player.setGameMode(GameMode.CREATIVE);

                Main.getPlugin(Main.class).getLogger().info(Main.getPlugin(Main.class).getGamemodeList().toString());

                player.sendMessage("You are now vanished!");
            } else {
                sender.sendMessage("You have no permission to do that!");
            }
        } else {
            sender.sendMessage("You need to be online to do that!");
        }

        return false;
    }
}
