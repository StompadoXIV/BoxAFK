package me.boxafk;

import lombok.Getter;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConfigManager {

    private String reasonNotPermitted;
    private String afkActive;
    private String afkAtive;
    private String inAfk;
    private String kick;

    private boolean isAnnounce;
    private List<String> announceEntryAFK;
    private List<String> announceExitAFK;

    private boolean blockCurseWords;
    private List<String> curseWords;

    private boolean isAtiveKick;
    private long timeKick;

    private double height;
    private List<String> hologram;

    public void loadConfig() {

        val config = BoxAFK.getInstance().getConfig();

        reasonNotPermitted = config.getString("Mensagens.PalavraInadequada");
        afkActive = config.getString("Mensagens.ModoAFKJaAtivo");
        afkAtive = config.getString("Mensagens.ModoAFKAtivado");
        inAfk = config.getString("Mensagens.EmModoAFK");
        kick = config.getString("Mensagens.Kick").replace("&", "§");

        isAnnounce = config.getBoolean("Configuracoes.AnuncioAFK.Ativar");
        announceEntryAFK = config.getStringList("Configuracoes.AnuncioAFK.AFKAtivo");
        announceEntryAFK = announceEntryAFK.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
        announceExitAFK = config.getStringList("Configuracoes.AnuncioAFK.AFKDesativo");
        announceExitAFK = announceExitAFK.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

        blockCurseWords = config.getBoolean("Configuracoes.Palavroes.Bloquear");
        curseWords = config.getStringList("Configuracoes.Palavroes.Palavroes");

        isAtiveKick = config.getBoolean("Configuracoes.TempoMaximo.Ativar");
        timeKick = config.getLong("Configuracoes.TempoMaximo.Tempo");

        height = config.getDouble("Configuracoes.Holograma.Altura");
        hologram = config.getStringList("Configuracoes.Holograma.Linhas");
        hologram = hologram.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

    }

}
