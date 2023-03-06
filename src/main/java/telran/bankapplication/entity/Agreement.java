package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.bankapplication.entity.enums.AccountProductStatus;
import telran.bankapplication.entity.enums.AccountType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name="agreement")
public class Agreement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account accountId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    @Basic
    @Column(name = "interest_rate", nullable = true, precision = 4)
    private BigDecimal interestRate;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private AccountProductStatus status;
    @Basic
    @Column(name = "sum", nullable = true, precision = 2)
    private BigDecimal sum;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return status == agreement.status && id.equals(agreement.id) && accountId.equals(agreement.accountId)
                && productId.equals(agreement.productId) && interestRate.equals(agreement.interestRate)
                && sum.equals(agreement.sum) && createdAt.equals(agreement.createdAt) && updatedAt.equals(agreement.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, productId);
    }
}