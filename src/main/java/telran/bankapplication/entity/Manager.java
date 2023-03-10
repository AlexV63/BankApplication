package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
public class Manager {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "status", nullable = false)
    private byte status;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Set<Client> clients = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Set<Product> products = new HashSet<>();
}

