package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import telran.bankapplication.entity.enums.CurrencyType;
import telran.bankapplication.entity.enums.ProductStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @Basic
    @Column(name = "name", nullable = true, length = 70)
    private String name;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private ProductStatus status;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code", nullable = true)
    private CurrencyType currencyCode;
    @Basic
    @Column(name = "interest_rate", nullable = true, precision = 4)
    private BigDecimal interestRate;
    @Basic
    @Column(name = "limites", nullable = true)
    private Integer limites;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private Set<Agreement> agreements = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


