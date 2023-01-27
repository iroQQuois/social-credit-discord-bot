package ru.iroqquois.discordbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "social_credits")
public class SocialCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Long value = 2000L;

    public SocialCredit(Long id, User user) {
        this.id = id;
        this.user = user;
        this.value = 2000L;
    }
}
