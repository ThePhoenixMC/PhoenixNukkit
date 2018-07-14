package com.lss233.phoenix.nukkit.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.event.cause.Cause;
import com.lss233.phoenix.event.entity.MoveEntityEvent;
import com.lss233.phoenix.event.network.ClientConnectionEvent;
import com.lss233.phoenix.world.Location;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

public class PlayerListener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {

        Cause cause = Cause.builder()
                .add("player", getTransformer().toPhoenix(event.getPlayer()))
                .build();
        Phoenix.getEventManager().fire((ClientConnectionEvent.Join) () -> cause);
    }

    @EventHandler
    public void transform(PlayerQuitEvent event) {
        Phoenix.getEventManager().fire((ClientConnectionEvent.Disconnect) () -> Cause.builder()
                .add("player", getTransformer().toPhoenix(event.getPlayer()))
                .build());
    }

    @EventHandler
    public void transform (PlayerLoginEvent event) {
        Phoenix.getEventManager().fire((ClientConnectionEvent.Login) () -> Cause.builder()
                .add("player", getTransformer().toPhoenix(event.getPlayer()))
                .build());
    }

    @EventHandler
    public void transform(PlayerMoveEvent event) {
        Cause cause = Cause.builder()
                .add("entity", getTransformer().toPhoenix(event.getPlayer()))
                .add("to", getTransformer().toPhoenix(event.getTo()))
                .add("from", getTransformer().toPhoenix(event.getFrom()))
                .build();
        Phoenix.getEventManager().fire(new MoveEntityEvent() {
            @Override
            public void setTo(Location location) {
                event.setTo(getTransformer().toNukkit(location));
            }

            @Override
            public boolean isCancelled() {
                return event.isCancelled();
            }

            @Override
            public void setCancelled(boolean cancel) {
                event.setCancelled(cancel);
            }

            @Override
            public Cause getCause() {
                return cause;
            }
        });
    }

    @EventHandler
    public void transform(PlayerToggleSneakEvent event) {
        Phoenix.getEventManager().fire(new com.lss233.phoenix.event.player.PlayerToggleSneakEven() {

            @Override
            public boolean isSneaking() {
                return event.isSneaking();
            }

            @Override
            public Cause getCause() {
                return Cause.builder()
                        .add("player", getTransformer().toPhoenix(event.getPlayer()))
                        .build();
            }
        });
    }

}
