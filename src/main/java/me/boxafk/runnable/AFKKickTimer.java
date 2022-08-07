package me.boxafk.runnable;

import lombok.val;
import me.boxafk.BoxAFK;
import me.boxafk.ConfigManager;
import me.boxafk.dao.PlayerAFKDAO;
import me.boxafk.utils.TimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AFKKickTimer extends BukkitRunnable {

    private final ConfigManager settings = BoxAFK.getInstance().getSettings();

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            val afk = PlayerAFKDAO.findPlayer(player.getName());
            if (afk == null) return;

            if (settings.isAtiveKick()) {
                val time = settings.getTimeKick() * 60000;

                if (afk.getTime() >= time) {
                    player.kickPlayer(settings.getKick().replace("{tempo}", TimeFormatter.format(time)).replace("%nl%", "\n"));
                }
            }
        });
    }
}