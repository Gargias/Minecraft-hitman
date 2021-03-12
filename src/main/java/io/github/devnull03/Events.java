package io.github.devnull03;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Events implements Listener {

    @EventHandler
    public static void onDamage(EntityDamageEvent event){
        if (!(HitmanPlugin.started)){
            return;
        }
        if (HitmanPlugin.breakTime) {
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }
}
