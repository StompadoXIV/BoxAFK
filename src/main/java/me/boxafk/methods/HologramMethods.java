package me.boxafk.methods;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.boxafk.BoxAFK;
import me.boxafk.ConfigManager;
import me.boxafk.model.PlayerAFK;
import me.boxafk.utils.BoxUtils;
import me.boxafk.utils.TimeFormatter;
import org.bukkit.Location;

import java.util.List;
import java.util.stream.Collectors;

public class HologramMethods {

    private static final ConfigManager settings = BoxAFK.getInstance().getSettings();

    public static void createHologram(PlayerAFK playerAFK, Location location) {
        List<String> hologram = settings.getHologram();

        hologram = hologram.stream().map(h -> h.replace("{motivo}", playerAFK.getReason()).replace("{tempo}", TimeFormatter.format(playerAFK.getTime()))).collect(Collectors.toList());
        DHAPI.createHologram(BoxUtils.Serializer(location), location.clone().add(0.5, settings.getHeight(), 0.5), hologram);
    }

    public static void updateHologram(PlayerAFK playerAFK, Location location) {
        List<String> hologram = settings.getHologram();

        hologram = hologram.stream().map(h -> h.replace("{motivo}", playerAFK.getReason()).replace("{tempo}", TimeFormatter.format(playerAFK.getTime()))).collect(Collectors.toList());
        DHAPI.setHologramLines(getHologram(location), hologram);
    }

    public static void removeHologram(Location location) {
        if (getHologram(location) != null)
            getHologram(location).delete();
    }

    private static Hologram getHologram(Location location) {
        return DHAPI.getHologram(BoxUtils.Serializer(location));
    }
}