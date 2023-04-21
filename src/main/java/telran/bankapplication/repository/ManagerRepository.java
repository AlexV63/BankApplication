package telran.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telran.bankapplication.entity.Manager;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    @Query("SELECT m from Manager m where m.firstName=?1")
    Optional<Manager> findByName(String name);

    @Query("SELECT m from Manager m where m.id=?1")
    Optional<Manager> findById(String UUID);
}