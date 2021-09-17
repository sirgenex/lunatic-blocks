package br.com.lunaticmc.blocks.object.controller;

import br.com.lunaticmc.blocks.object.BlockPlayer;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class BlockPlayerController {

    @Getter private static final BlockPlayerController instance = new BlockPlayerController();

    private final HashMap<String, BlockPlayer> cache = new HashMap<>();

    public void create(BlockPlayer model){
        cache.put(model.getName(), model);
    }

    public BlockPlayer get(String name){
        return hasAccount(name) ? cache.get(name) : new BlockPlayer(name, 0, 0);
    }

    public boolean hasAccount(String name){
        return cache.containsKey(name);
    }

    public void saveAll(FileConfiguration c){
        cache.forEach((player, value) -> {
            c.set("players." + player + ".balance", value.getBalance());
            c.set("players." + player + ".total", value.getTotal());
        });
    }

}
