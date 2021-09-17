package br.com.lunaticmc.blocks.config;

import br.com.lunaticmc.blocks.Blocks;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigurationData {

    @Getter private static final ConfigurationData instance = new ConfigurationData();

    private final FileConfiguration c = Blocks.getInstance().getConfig();

    public String permission = c.getString("permission");

    public List<String> balance_you = c.getStringList("messages.balance.you");
    public List<String> balance_others = c.getStringList("messages.balance.others");
    public List<String> help_player = c.getStringList("messages.help.normal");
    public List<String> help_admin = c.getStringList("messages.help.admin");
    public List<String> without_permission = c.getStringList("messages.without_permission");
    public List<String> inexistent_player = c.getStringList("messages.inexistent_player");
    public List<String> set = c.getStringList("messages.setted");
    public List<String> add = c.getStringList("messages.added");
    public List<String> remove = c.getStringList("messages.removed");
    public List<String> invalid_integer = c.getStringList("messages.invalid_integer");
    public List<String> without_money = c.getStringList("messages.without_money");
    public List<String> player_without_money = c.getStringList("messages.player_without_money");

}
