package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name="transaction")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_account_id", nullable = false)
    private Account debitAccountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_account_id", nullable = false)
    private Account creditAccountId;
    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;
    @Basic
    @Column(name = "amount", nullable = true, precision = 4)
    private BigDecimal amount;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return type == that.type && Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId, type, amount, description, createdAt);
    }
}

