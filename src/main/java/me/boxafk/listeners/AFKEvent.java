package me.boxafk.listeners;

import lombok.val;
import me.boxafk.BoxAFK;
import me.boxafk.ConfigManager;
import me.boxafk.dao.PlayerAFKDAO;
import me.boxafk.methods.HologramMethods;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AFKEvent implements Listener {

    private final ConfigManager settings;

    public AFKEvent(BoxAFK main) {
        settings = main.getSettings();
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    void afkEvent(PlayerMoveEvent e) {
        val p = e.getPlayer();
        val afk = PlayerAFKDAO.findPlayer(p.getName());
        if (afk == null) return;

        PlayerAFKDAO.getPlayerAFKMap().remove(afk);
        HologramMethods.removeHologram(p.getLocation().getBlock().getLocation());

        if (settings.isAnnounce()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                for (val msg : settings.getAnnounceExitAFK())
                    player.sendMessage(msg.replace("{jogador}", p.getName()));

            });
        }
    }

    @EventHandler
    void executeCommand(PlayerCommandPreprocessEvent e) {
        val p = e.getPlayer();
        val cmd = e.getMessage();

        if (!cmd.equalsIgnoreCase("afk")) return;

        val afk = PlayerAFKDAO.findPlayer(p.getName());
        if (afk == null) return;

        PlayerAFKDAO.getPlayerAFKMap().remove(afk);
        HologramMethods.removeHologram(p.getLocation().getBlock().getLocation());

        if (settings.isAnnounce()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                for (val msg : settings.getAnnounceExitAFK())
                    player.sendMessage(msg.replace("{jogador}", p.getName()));

            });
        }
    }

    @EventHandler
    void quitServer(PlayerKickEvent e) {
        val p = e.getPlayer();
        val afk = PlayerAFKDAO.findPlayer(p.getName());
        if (afk == null) return;

        PlayerAFKDAO.getPlayerAFKMap().remove(afk);
        HologramMethods.removeHologram(p.getLocation().getBlock().getLocation());

    }
}