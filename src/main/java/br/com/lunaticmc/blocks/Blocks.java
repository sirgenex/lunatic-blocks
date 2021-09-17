package br.com.lunaticmc.blocks;

import br.com.lunaticmc.blocks.adapter.BlockPlayerAdapter;
import br.com.lunaticmc.blocks.command.BlocksCommand;
import br.com.lunaticmc.blocks.event.listener.BlockBreakListener;
import br.com.lunaticmc.blocks.event.listener.BlockChangeListener;
import br.com.lunaticmc.blocks.hook.PlaceholderHook;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import br.com.lunaticmc.blocks.task.BackgroundTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Blocks extends JavaPlugin {

    @Getter private static Blocks instance;

    private File file = null;
    private FileConfiguration fileConfiguration = null;

    @Override
    public void onEnable() {
        getLogger().info("Starting plugin...");
        instance = this;

        getLogger().info("Loading config...");
        saveDefaultConfig();
        getLogger().info("Config loaded!");

        getLogger().info("Loading database...");
        File verifier = new File(getDataFolder(), "db.yml");
        if (!verifier.exists()) saveResource("db.yml", false);
        BlockPlayerAdapter.getInstance().adapt(getDB());
        getLogger().info("Database loaded!");

        getLogger().info("Registering commands...");
        getCommand("blocks").setExecutor(new BlocksCommand());
        getLogger().info("Commands registered!");

        getLogger().info("Trying to hook into PlaceholderAPI...");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook().register();
            getLogger().info("PlaceholderAPI hooked successfully.");
        } else getLogger().warning("PlaceholderAPI can't be found.");

        getLogger().info("Registering listeners...");
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockChangeListener(), this);
        getLogger().info("Listeners registered!");

        getLogger().info("Starting background task...");
        new BackgroundTask().runTaskTimerAsynchronously(this, 0,(20L *getConfig().getInt("task_timer"))*60);
        getLogger().info("Background task started!");

        getLogger().info("Plugin started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling plugin...");

        getLogger().info("Saving data...");
        BlockPlayerController.getInstance().saveAll(getDB());
        saveDB();
        reloadDB();
        getLogger().info("Data saved successfully!");

        getLogger().info("Plugin disabled!");
    }

    public FileConfiguration getDB() {
        if (this.fileConfiguration == null) {
            this.file = new File(getDataFolder(), "db.yml");
            this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        }
        return this.fileConfiguration;
    }

    public void saveDB() {
        try { getDB().save(this.file); } catch (Exception ignored) {}
    }

    public void reloadDB() {
        if (this.file == null) this.file = new File(getDataFolder(), "db.yml");
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        YamlConfiguration db = YamlConfiguration.loadConfiguration(this.file);
        this.fileConfiguration.setDefaults(db);
    }

}