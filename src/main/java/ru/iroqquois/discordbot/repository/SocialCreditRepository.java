package ru.iroqquois.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iroqquois.discordbot.entity.SocialCredit;

@Repository
public interface SocialCreditRepository extends JpaRepository<SocialCredit, Long> {
    boolean existsByUserId(Long id);
}
