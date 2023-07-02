package pl.pilleow.quizapp.quiz;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.pilleow.quizapp.question.QuestionRepository;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public Quiz getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz z ID="+id+" nie istnieje.")
        );
        return quiz;
    }

    public void createQuiz(Quiz quiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findQuizByTytul(quiz.getTytul());
        if (optionalQuiz.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Istnieje quiz o takim samym tytule. ID="+optionalQuiz.get().getId());
        }
        quizRepository.save(quiz);
    }

    @Transactional
    public void editQuiz(Long id, String tytul, String opis, Long author_id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz z ID=" + id + " nie istnieje.")
        );

        // tytul
        if (tytul != null && !tytul.isEmpty() && !Objects.equals(tytul, quiz.getTytul())) {
            Optional<Quiz> optionalQuiz = quizRepository.findQuizByTytul(tytul);
            if (optionalQuiz.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Istnieje quiz o takim samym tytule. ID=" + optionalQuiz.get().getId());
            }
            quiz.setTytul(tytul);
        }

        // opis
        if (opis != null && !opis.isEmpty() && !Objects.equals(opis, quiz.getOpis())) {
            quiz.setOpis(opis);
        }

        // author_id
        if (author_id != null && !Objects.equals(author_id, quiz.getAuthor_id())) {
            quiz.setAuthor_id(author_id);
        }
    }

    @Transactional
    public void deleteQuiz(Long id) {
        boolean exists = quizRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz z ID="+id+" nie istnieje.");
        }
        questionRepository.deleteQuestionsByQuizId(id);
        quizRepository.deleteById(id);
    }
}
