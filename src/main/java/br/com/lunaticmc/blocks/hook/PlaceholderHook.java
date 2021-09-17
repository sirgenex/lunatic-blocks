package br.com.lunaticmc.blocks.hook;

import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "blocks";
    }

    @Override
    public String getPlugin() {
        return "lunatic-blocks";
    }

    @Override
    public String getAuthor() {
        return "SrGeneX";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if(player == null) return "";
        if(s.equalsIgnoreCase("balance")) return BlockPlayerController.getInstance().get(player.getName()).getFormatted();
        return "";
    }
}
