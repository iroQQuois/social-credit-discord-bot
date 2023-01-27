package ru.iroqquois.discordbot.service;

import ru.iroqquois.discordbot.entity.User;

import java.util.Collection;
import java.util.Set;

public interface UserService {
    User getEntityById(Long id);

    User getEntityByDiscordId(String discordId);

    Set<User> getEntities();

    boolean existByDiscordId(String id);

    Set<User> create(Collection<User> newUsers);
}
