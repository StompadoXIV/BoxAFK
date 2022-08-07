package me.boxafk;

import lombok.Getter;
import me.boxafk.commands.AFKCommand;
import me.boxafk.listeners.AFKEvent;
import me.boxafk.listeners.ChatEvent;
import me.boxafk.runnable.AFKCurrentTimer;
import me.boxafk.runnable.AFKKickTimer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BoxAFK extends JavaPlugin {

    @Getter
    private static BoxAFK Instance;
    private ConfigManager settings;

    public void onEnable() {
        Instance = this;
        registerYaml();
        registerCommands();
        registerEvents();
        sendMessage();

        loadTimers();
    }

    private void registerCommands() {
        getCommand("afk").setExecutor(new AFKCommand());
    }

    private void registerEvents() {
        new AFKEvent(this);
        new ChatEvent(this);
    }

    private void registerYaml() {
        settings = new ConfigManager();
        saveDefaultConfig();
        settings.loadConfig();
    }

    private void loadTimers() {
        new AFKKickTimer().runTaskTimer(this, 20L, 20L);
        new AFKCurrentTimer().runTaskTimerAsynchronously(this, 20L * 60, 20L * 60);

        Bukkit.getConsoleSender().sendMessage("§4[BoxAFK] §aOs §eRunnables §aforam carregados com sucesso.");
    }

    private void sendMessage() {
        Bukkit.getConsoleSender().sendMessage("§4[BoxAFK] §fCriado por §b[Stompado]");
        Bukkit.getConsoleSender().sendMessage("§b[Discord] §fhttps://discord.gg/Z6PbQgdweB");
        Bukkit.getConsoleSender().sendMessage("§4[BoxAFK] §aO plugin §4BoxAFK §afoi iniciado com sucesso.");
    }
}