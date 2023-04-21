package telran.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import telran.bankapplication.entity.Client;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE Client a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableClient(String email);
}
