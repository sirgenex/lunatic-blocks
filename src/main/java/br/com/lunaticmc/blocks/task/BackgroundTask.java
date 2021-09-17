package br.com.lunaticmc.blocks.task;

import br.com.lunaticmc.blocks.Blocks;
import br.com.lunaticmc.blocks.object.controller.BlockPlayerController;
import org.bukkit.scheduler.BukkitRunnable;

public class BackgroundTask extends BukkitRunnable {

    @Override
    public void run() {
        BlockPlayerController.getInstance().saveAll(Blocks.getInstance().getDB());
    }

}
