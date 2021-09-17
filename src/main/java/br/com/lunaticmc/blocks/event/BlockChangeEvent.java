package br.com.lunaticmc.blocks.event;

import br.com.lunaticmc.blocks.object.operation.OperationType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class BlockChangeEvent extends Event implements Cancellable {

    private final String player;
    @Setter private boolean cancelled;
    private final double from;
    private final double to;
    private final OperationType operation;

    public BlockChangeEvent(String player, double from, double to, OperationType operation) {
        this.player = player;
        setCancelled(false);
        this.from = from;
        this.to = to;
        this.operation = operation;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}