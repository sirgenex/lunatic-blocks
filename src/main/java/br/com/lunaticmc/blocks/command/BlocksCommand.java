package br.com.lunaticmc.blocks.command;

import br.com.lunaticmc.blocks.config.ConfigurationData;
import br.com.lunaticmc.blocks.manager.FormatManager;
import br.com.lunaticmc.blocks.object.BlockPlayer;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("deprecation")
public class BlocksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final ConfigurationData c = ConfigurationData.getInstance();

        BlockPlayer player = BlockPlayerController.getInstance().get(sender.getName());

        if(args.length == 0) c.balance_you.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{balance}", player.getFormatted())));
        else{

            if(args[0].equalsIgnoreCase("help")){
                if(sender.hasPermission(c.permission)) c.help_admin.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                else c.help_player.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                return true;
            }

            if(args.length == 3) {
                OfflinePlayer jg = Bukkit.getOfflinePlayer(args[1]);
                double amount;
                try {
                    amount = Double.parseDouble(args[2]);
                } catch (Exception ignored) {
                    c.invalid_integer.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                    return true;
                }
                if (!jg.hasPlayedBefore()) {
                    c.inexistent_player.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                    return true;
                }
                if (amount < 0 || args[2].equalsIgnoreCase("NaN") || args[2].equalsIgnoreCase("null")) {
                    c.invalid_integer.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                    return true;
                }
                if (args[0].equalsIgnoreCase("adicionar") || args[0].equalsIgnoreCase("remover") || args[0].equalsIgnoreCase("definir")) {
                    if (!sender.hasPermission(c.permission)) {
                        c.without_permission.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
                        return true;
                    }
                }
                BlockPlayer blockJg = BlockPlayerController.getInstance().get(jg.getName());
                if (args[0].equalsIgnoreCase("definir")) {
                    blockJg.set(amount);
                    c.set.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{player}", jg.getName()).replace("{amount}", FormatManager.getInstance().format(amount)).replace("{balance}", blockJg.getFormatted())));
                    return true;
                }
                if (args[0].equalsIgnoreCase("adicionar")) {
                    blockJg.add(amount);
                    c.add.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{player}", jg.getName()).replace("{amount}", FormatManager.getInstance().format(amount)).replace("{balance}", blockJg.getFormatted())));
                    return true;
                }
                if (args[0].equalsIgnoreCase("remover")) {
                    if(blockJg.has(amount)) {
                        blockJg.remove(amount);
                        c.remove.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{player}", jg.getName()).replace("{amount}", FormatManager.getInstance().format(amount)).replace("{balance}", blockJg.getFormatted())));
                        return true;
                    }else c.player_without_money.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{player}", jg.getName())));
                    return true;
                }
            }
            OfflinePlayer jg = Bukkit.getOfflinePlayer(args[0]);
            if(jg.hasPlayedBefore()) c.balance_others.forEach(msg -> sender.sendMessage(msg.replace("&", "§").replace("{player}", jg.getName()).replace("{balance}", BlockPlayerController.getInstance().get(jg.getName()).getFormatted())));
            else c.inexistent_player.forEach(msg -> sender.sendMessage(msg.replace("&", "§")));
        }

        return false;
    }
}
