package me.boxafk.commands;

import lombok.val;
import me.boxafk.BoxAFK;
import me.boxafk.ConfigManager;
import me.boxafk.dao.PlayerAFKDAO;
import me.boxafk.methods.HologramMethods;
import me.boxafk.model.PlayerAFK;
import me.boxafk.utils.BoxUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AFKCommand implements CommandExecutor {

    private final ConfigManager settings = BoxAFK.getInstance().getSettings();

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {

        if (s instanceof ConsoleCommandSender) {
            s.sendMessage("§cO console não executa esse comando.");
            return true;
        }

        val p = (Player)s;
        if (!p.hasPermission("boxafk.afk")) {
            p.sendMessage("§cVocê não possui permissão para esse comando.");
            return true;
        }

        if (a.length > 0) {
            val inAFK = PlayerAFKDAO.findPlayer(p.getName());
            if (inAFK != null) {
                BoxUtils.sendMessage(p, settings.getAfkActive());
                return true;
            }

            val reason = String.join(" ", Arrays.copyOfRange(a, 0, a.length));

            if (settings.isBlockCurseWords()) {
                if (settings.getCurseWords().contains(reason.toLowerCase())) {
                    BoxUtils.sendMessage(p, settings.getReasonNotPermitted());
                    return true;
                }
            }

            val afk = new PlayerAFK(p.getName(), reason, 0);
            PlayerAFKDAO.getPlayerAFKMap().add(afk);
            HologramMethods.createHologram(afk, p.getLocation().getBlock().getLocation());
            BoxUtils.sendMessage(p, settings.getAfkAtive());

            if (settings.isAnnounce()) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    for (val msg : settings.getAnnounceEntryAFK())
                        player.sendMessage(msg.replace("{jogador}", p.getName()));
                });
            }

        } else {
            BoxUtils.sendMessage(p, "§cUtilize /afk (motivo).");
        }
        return false;
    }
}