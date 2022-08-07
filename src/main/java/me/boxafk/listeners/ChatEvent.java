package me.boxafk.listeners;

import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
import lombok.val;
import me.boxafk.BoxAFK;
import me.boxafk.ConfigManager;
import me.boxafk.dao.PlayerAFKDAO;
import me.boxafk.utils.BoxUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatEvent implements Listener {

    private final ConfigManager settings;

    public ChatEvent(BoxAFK main) {
        settings = main.getSettings();
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    void chatEvent(PrivateMessageEvent e) {
        val p = e.getSender();
        val t = e.getReceiver();

        val afk = PlayerAFKDAO.findPlayer(t.getName());
        if (afk == null) return;

        e.setCancelled(true);
        BoxUtils.sendMessage(p, settings.getInAfk());
    }
}