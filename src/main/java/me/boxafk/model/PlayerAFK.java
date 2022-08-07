package me.boxafk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlayerAFK {

    private String playerName;
    private String reason;
    private long time;

}