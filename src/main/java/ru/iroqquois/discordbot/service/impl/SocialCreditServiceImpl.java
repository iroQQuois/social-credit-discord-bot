package ru.iroqquois.discordbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iroqquois.discordbot.repository.SocialCreditRepository;
import ru.iroqquois.discordbot.core.Punishments;
import ru.iroqquois.discordbot.entity.SocialCredit;
import ru.iroqquois.discordbot.service.SocialCreditService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
class SocialCreditServiceImpl implements SocialCreditService {
    private final SocialCreditRepository repository;

    @Override
    public SocialCredit getEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Long violationOfCurfew(Long id) {
        SocialCredit entity = getEntityById(id);
        entity.setValue(entity.getValue() - Punishments.getViolationOfCurfew());
        repository.save(entity);

        return entity.getValue();
    }

    @Override
    public Set<SocialCredit> create(Collection<SocialCredit> entities) {
        return new HashSet<>(repository.saveAll(entities));
    }

    @Override
    public boolean existByUserId(Long id) {
        return repository.existsByUserId(id);
    }
}
