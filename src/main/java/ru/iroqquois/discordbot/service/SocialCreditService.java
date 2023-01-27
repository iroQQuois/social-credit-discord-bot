package ru.iroqquois.discordbot.service;

import ru.iroqquois.discordbot.entity.SocialCredit;

import java.util.Collection;
import java.util.Set;

public interface SocialCreditService {
    SocialCredit getEntityById(Long id);

    Long violationOfCurfew(Long id);

    Set<SocialCredit> create(Collection<SocialCredit> entities);

    boolean existByUserId(Long id);
}
