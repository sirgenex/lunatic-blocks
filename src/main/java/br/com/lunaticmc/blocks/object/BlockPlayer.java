package br.com.lunaticmc.blocks.object;

import br.com.lunaticmc.blocks.event.BlockChangeEvent;
import br.com.lunaticmc.blocks.manager.FormatManager;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import br.com.lunaticmc.blocks.object.operation.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

@Getter
@Setter
@AllArgsConstructor
public class BlockPlayer {

    private String name;
    private double balance;
    private double total;

    public void add(double amount){
        if(!BlockPlayerController.getInstance().hasAccount(name)) BlockPlayerController.getInstance().create(this);
        BlockChangeEvent event = new BlockChangeEvent(name, getBalance(), getBalance()+amount, OperationType.ADD);
        Bukkit.getPluginManager().callEvent(event);
        if(!BlockPlayerController.getInstance().hasAccount(name)) BlockPlayerController.getInstance().create(this);
    }

    public void remove(double amount){
        if(!BlockPlayerController.getInstance().hasAccount(name)) BlockPlayerController.getInstance().create(this);
        BlockChangeEvent event = new BlockChangeEvent(name, getBalance(), getBalance()-amount, OperationType.REMOVE);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void set(double amount){
        if(!BlockPlayerController.getInstance().hasAccount(name)) BlockPlayerController.getInstance().create(this);
        BlockChangeEvent event = new BlockChangeEvent(name, getBalance(), amount, OperationType.SET);
        Bukkit.getPluginManager().callEvent(event);
    }

    public boolean has(double amount){
        return getBalance() >= amount;
    }

    public String getFormatted(){
        return FormatManager.getInstance().format(getBalance());
    }

}
