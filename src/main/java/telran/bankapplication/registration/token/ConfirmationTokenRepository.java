package telran.bankapplication.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import telran.bankapplication.entity.Client;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Transactional
    @Modifying
    void deleteByClient(Client client);

    @Query("SELECT c FROM ConfirmationToken c where c.token=?1")
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 where c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
