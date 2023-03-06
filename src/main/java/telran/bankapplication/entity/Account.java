package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name="account")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;   //UUID

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
    @Basic
    @Column(name = "balance", nullable = true, precision = 4)
    private BigDecimal balance;
    @Basic
    @Column(name = "currency_code", nullable = true)
    private Integer currencyCode;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Agreement> agreements;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debitAccount")
    private Set<Transaction> debitTransactionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditAccount")
    private Set<Transaction> creditTransactionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return type == account.type && status == account.status && Objects.equals(id, account.id) && Objects.equals(clientId, account.clientId) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance) && Objects.equals(currencyCode, account.currencyCode) && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt) && Objects.equals(agreements, account.agreements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, name, type, status, balance, currencyCode, createdAt, updatedAt, agreements);
    }
}
