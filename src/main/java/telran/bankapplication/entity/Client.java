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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private ClientStatus status;

    @Basic
    @Column(name = "tax_code", nullable = true, length = 20)
    private String taxCode;
    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = true, length = 60)
    private String email;
    @Basic
    @Column(name = "address", nullable = true, length = 80)
    private String address;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

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
