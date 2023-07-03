package pl.pilleow.quizapp.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT u FROM Profile u WHERE u.email=?1")
    Optional<Profile> findProfileByEmail(String email);
    @Query("SELECT u FROM Profile u WHERE u.nazwa_uzytk=?1")
    Optional<Profile> findProfileByNazwaUzytk(String nazwa_uzytk);
}
