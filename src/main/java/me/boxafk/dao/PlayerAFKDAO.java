package me.boxafk.dao;

import lombok.Getter;
import me.boxafk.model.PlayerAFK;

import java.util.ArrayList;
import java.util.List;

public class PlayerAFKDAO {

    @Getter
    private static List<PlayerAFK> playerAFKMap = new ArrayList<>();

    public static PlayerAFK findPlayer(String playerName) {
        return playerAFKMap.stream().filter(playerAFK -> playerAFK.getPlayerName().equals(playerName)).findFirst().orElse(null);
    }
}