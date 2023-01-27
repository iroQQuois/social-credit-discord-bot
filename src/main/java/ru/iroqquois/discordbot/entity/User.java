package ru.iroqquois.discordbot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discord_id", nullable = false)
    private String discordId;

    @OneToOne(mappedBy = "user")
    private SocialCredit socialCredit;
}
