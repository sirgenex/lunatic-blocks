package br.com.lunaticmc.blocks.event.listener;

import br.com.lunaticmc.blocks.event.BlockChangeEvent;
import br.com.lunaticmc.blocks.object.BlockPlayer;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import br.com.lunaticmc.blocks.object.operation.OperationType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class BlockChangeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockChange(BlockChangeEvent e){
        BlockPlayer player = BlockPlayerController.getInstance().get(e.getPlayer());
        player.setBalance(e.getTo());
        if(e.getOperation().equals(OperationType.ADD)) player.setTotal(player.getTotal()+(e.getTo()-e.getFrom()));
    }

}
