package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import telran.bankapplication.entity.enums.ClientStatus;

import java.sql.Timestamp;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="client")
public class Client {
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private ClientStatus status;

    @Column(name = "tax_code", nullable = true, length = 20)
    private String taxCode;

    @Column(name = "first_name", nullable = true, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = true, length = 50)
    private String lastName;

    @Column(name = "email", nullable = true, length = 60)
    private String email;

    @Column(name = "address", nullable = true, length = 80)
    private String address;

    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client")
    private Set<Account> accountList = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
