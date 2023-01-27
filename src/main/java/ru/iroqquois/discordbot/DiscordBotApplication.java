package ru.iroqquois.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.iroqquois.discordbot.core.SlashCommands;
import ru.iroqquois.discordbot.listener.OnSlashCommandInteraction;
import ru.iroqquois.discordbot.listener.VoiceJoinEvent;

@SpringBootApplication
public class DiscordBotApplication implements CommandLineRunner {
    @Autowired
    private OnSlashCommandInteraction onSlashCommandInteraction;

    @Autowired
    private VoiceJoinEvent voiceJoinEvent;

    public static void main(String[] args) {
        SpringApplication.run(DiscordBotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JDA bot = JDABuilder.createDefault(""). // insert your code here
                setActivity(Activity.playing("with your mom"))
                .addEventListeners(voiceJoinEvent)
                .addEventListeners(onSlashCommandInteraction)
                .build().awaitReady();

        bot.updateCommands().addCommands(
                Commands.slash(SlashCommands.User.USER_SCAN, "Отсканировать юзеров на сервере"),
                Commands.slash(SlashCommands.User.UDPATE_SOCIAL_CREDIT_DATA, "Обновить данные по соц. кредиту")
        ).queue();
    }


}
