package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.bankapplication.entity.Client;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "token")
public class ConfirmationToken {

    @Id
    private UUID id;

    @Column(nullable=false)
    private String token;
    @Column(nullable=false)
    LocalDateTime createdAt;
    @Column(nullable=false)
    LocalDateTime expiresAt;
    LocalDateTime confirmedAt;




    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;

    public ConfirmationToken(Client client) {
        this.id = UUID.randomUUID();
        this.token = String.valueOf(UUID.randomUUID());
        this.createdAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
        this.client = client;
    }
}