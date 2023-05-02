package telran.bankapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
public class Client implements UserDetails {
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
    private String name;

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

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private Manager manager;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client")
    private Set<Account> accountList = new HashSet<>();

    public Client(String firstName, String lastName, String email, String password) {
        this.id= UUID.randomUUID();
        this.firstName = firstName;
        this.name = lastName;
        this.email = email;
        this.password = password;
        this.status = ClientStatus.PENDING;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == ClientStatus.ACTIVE;
    }
}
