package ru.iroqquois.discordbot.listener;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.iroqquois.discordbot.core.GuildConfig;
import ru.iroqquois.discordbot.core.SlashCommands;
import ru.iroqquois.discordbot.entity.SocialCredit;
import ru.iroqquois.discordbot.entity.User;
import ru.iroqquois.discordbot.service.SocialCreditService;
import ru.iroqquois.discordbot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OnSlashCommandInteraction implements EventListener {
    private final UserService userService;
    private final SocialCreditService socialCreditService;

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent) {
            switch (((SlashCommandInteractionEvent) event).getName()) {
                case SlashCommands.User.USER_SCAN -> {
                    Set<User> notExistedUsers = new HashSet<>();
                    for (Member member : event.getJDA()
                            .getGuilds()
                            .get(0)
                            .getMembers()
                    ) {
                        for (Role role : member.getRoles()) {
                            if (role.getId().equals(GuildConfig.getTrackableRoleId())) {
                                if (!userService.existByDiscordId(member.getId())) {
                                    notExistedUsers.add(new User(null, member.getId(), null));
                                }
                            }
                        }
                    }
                    userService.create(notExistedUsers);
                    ((SlashCommandInteractionEvent) event).reply("Малютки отсканены, список обновился")
                            .setEphemeral(true)
                            .queue();
                }
                case SlashCommands.User.UDPATE_SOCIAL_CREDIT_DATA -> {
                    Set<User> users = userService.getEntities();
                    Set<SocialCredit> newSocialCredits = new HashSet<>();
                    for (User user : users) {
                        if (!socialCreditService.existByUserId(user.getId())) {
                            newSocialCredits.add(new SocialCredit(null, user));
                        }
                    }
                    socialCreditService.create(newSocialCredits);
                    ((SlashCommandInteractionEvent) event).reply("Список соц. кредита обновился")
                            .setEphemeral(true)
                            .queue();
                }
            }
        }
    }
}
