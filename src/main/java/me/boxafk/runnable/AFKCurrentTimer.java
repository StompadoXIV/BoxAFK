package me.boxafk.runnable;

import lombok.val;
import me.boxafk.dao.PlayerAFKDAO;
import me.boxafk.methods.HologramMethods;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AFKCurrentTimer extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {

            val afk = PlayerAFKDAO.findPlayer(player.getName());
            if (afk != null) {
                afk.setTime(afk.getTime() + 60000);
                HologramMethods.updateHologram(afk, player.getLocation().getBlock().getLocation());
            }
        });
    }
}