package ru.iroqquois.discordbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iroqquois.discordbot.entity.User;
import ru.iroqquois.discordbot.repository.UserRepository;
import ru.iroqquois.discordbot.service.UserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getEntityByDiscordId(String discordId) {
        return userRepository.findByDiscordId(discordId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Set<User> getEntities() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public boolean existByDiscordId(String id) {
        return userRepository.existsByDiscordId(id);
    }

    @Override
    public Set<User> create(Collection<User> newUsers) {
        return new HashSet<>(userRepository.saveAll(newUsers));
    }
}
