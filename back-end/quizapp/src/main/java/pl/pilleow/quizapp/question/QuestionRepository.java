package pl.pilleow.quizapp.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.pytanie=?1")
    Optional<Question> findQuestionByPytanie(String pytanie);

    @Modifying
    @Query("DELETE FROM Question WHERE quiz_id=?1")
    void deleteQuestionsByQuizId(Long quiz_id);
}
