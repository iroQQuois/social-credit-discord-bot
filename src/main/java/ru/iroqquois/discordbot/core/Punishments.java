package ru.iroqquois.discordbot.core;

import lombok.Getter;

public class Punishments {
    private static final Long VIOLATION_OF_CURFEW = 500L;

    public static Long getViolationOfCurfew() {
        return VIOLATION_OF_CURFEW;
    }
}
