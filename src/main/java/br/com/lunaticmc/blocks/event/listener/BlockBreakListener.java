package br.com.lunaticmc.blocks.event.listener;

import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e){
        if(e.isCancelled()) return;
        BlockPlayerController.getInstance().get(e.getPlayer().getName()).add(1);
    }

}
