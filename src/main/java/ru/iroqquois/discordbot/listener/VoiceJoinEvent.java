package ru.iroqquois.discordbot.listener;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.iroqquois.discordbot.core.GuildConfig;
import ru.iroqquois.discordbot.service.SocialCreditService;
import ru.iroqquois.discordbot.service.UserService;
import ru.iroqquois.discordbot.utils.TextChannelUtil;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class VoiceJoinEvent implements EventListener {
    private final SocialCreditService socialCreditService;
    private final UserService userService;

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof GuildVoiceUpdateEvent) {
            if (LocalTime.now().isBefore(LocalTime.of(22,0,0))) {
                JDA jda = event.getJDA();
                if (((GuildVoiceUpdateEvent) event).getChannelLeft() != null) {
                    Member suspect = ((GuildVoiceUpdateEvent) event).getEntity();
                    for (Role role : suspect.getRoles()) {
                        if (role.getId().equals(GuildConfig.getTrackableRoleId())) {
                            TextChannel channel = jda.getTextChannelById(GuildConfig.getTextChannelId());
                            if (channel != null) {
                                socialCreditService.violationOfCurfew(
                                        userService.getEntityByDiscordId(suspect.getId())
                                                .getSocialCredit()
                                                .getId()
                                );
                                TextChannelUtil.sendMessage(
                                        channel,
                                        "Харам, -500 social credit " + TextChannelUtil.getUserPing(suspect.getId())
                                );
                            } else {
                                throw new RuntimeException("Channel not found");
                            }
                        }
                    }
                }
            }
        }
    }
}
