package ru.iroqquois.discordbot.utils;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

@UtilityClass
public class TextChannelUtil {
    public void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    public String getUserPing(String userId) {
        return "<@" + userId + ">";
    }
}
