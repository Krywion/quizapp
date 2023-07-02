package pl.pilleow.quizapp.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.tytul=?1")
    Optional<Quiz> findQuizByTytul(String tytul);
}
