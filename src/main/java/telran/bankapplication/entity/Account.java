package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import telran.bankapplication.entity.enums.AccountStatus;
import telran.bankapplication.entity.enums.AccountType;
import telran.bankapplication.entity.enums.CurrencyType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private AccountStatus status;

    @Column(name = "balance", nullable = true, precision = 4)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code", nullable = true)
    private CurrencyType currencyCode;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    private Set<Agreement> agreements;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "debitAccount")
    private Set<Transaction> debitTransactionList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "creditAccount")
    private Set<Transaction> creditTransactionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && client.equals(account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client);
    }
}