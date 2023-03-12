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
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "name", nullable = true, length = 70)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code", nullable = true)
    private CurrencyType currencyCode;

    @Column(name = "interest_rate", nullable = true, precision = 4)
    private BigDecimal interestRate;

    @Column(name = "limites", nullable = true)
    private Integer limites;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;

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


