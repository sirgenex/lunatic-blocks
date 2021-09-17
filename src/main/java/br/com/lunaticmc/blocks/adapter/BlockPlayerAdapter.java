package br.com.lunaticmc.blocks.adapter;

import br.com.lunaticmc.blocks.object.BlockPlayer;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class BlockPlayerAdapter {

    @Getter private static final BlockPlayerAdapter instance = new BlockPlayerAdapter();

    public void adapt(FileConfiguration c){

        c.getConfigurationSection("players").getKeys(false).forEach(player -> {

            double balance = c.getDouble("players."+player+".balance");
            double total = c.getDouble("players."+player+".total");

            BlockPlayer model = new BlockPlayer(player, balance, total);

            BlockPlayerController.getInstance().create(model);

        });

    }

}
