package pl.pilleow.quizapp.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.tytul=?1")
    Optional<Quiz> findQuizByTytul(String tytul);

    @Query("SELECT q FROM Quiz q WHERE q.author_id =?1")
    List<Quiz> findQuizzesByAuthorId(Long authorId);
}
