package telran.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telran.bankapplication.entity.Client;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("SELECT c from Client c where c.lastName=?1")
    Optional<Client> findByName(String name);
}
