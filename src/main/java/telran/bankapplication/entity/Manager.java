package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import telran.bankapplication.entity.enums.AccountType;
import telran.bankapplication.entity.enums.ManagerStatus;

import java.sql.Timestamp;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
public class Manager {
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ManagerStatus status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "manager")
    private Set<Client> clients = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "manager")
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

