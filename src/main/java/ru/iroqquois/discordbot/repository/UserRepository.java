package ru.iroqquois.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iroqquois.discordbot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDiscordId(String id);

    Optional<User> findByDiscordId(String discordId);
}
