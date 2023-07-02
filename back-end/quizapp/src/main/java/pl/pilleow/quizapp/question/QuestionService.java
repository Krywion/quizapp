package pl.pilleow.quizapp.question;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.pilleow.quizapp.quiz.QuizRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Question getQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pytanie z ID=" + id + " nie istnieje.")
        );
        return question;
    }

    public void createQuestion(Question question) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByPytanie(question.getPytanie());
        if (optionalQuestion.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W podanym quizie (QuizID=" + question.getQuiz_id() + ") znajduje się pytanie o takiej samej treści.");
        }

        boolean quizExists = quizRepository.existsById(question.getQuiz_id());
        if (!quizExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz o podanym QuizID nie istneieje. Utwórz quiz o ID="+question.getQuiz_id()+" lub popraw QuizID.");
        }

        String[] odpowiedzi = question.getOdpowiedzi();
        for (int i = 0; i < odpowiedzi.length; ++i)  {
            for (int j = 0; j < odpowiedzi.length; ++j) {
                if (i == j) continue;
                else if (Objects.equals(odpowiedzi[i], odpowiedzi[j])) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wykryto identyczne odpowiedzi. Odpowiedzi nie mogą się powtarzać.");
                }
            }
        }
        questionRepository.save(question);
    }

    @Transactional
    public void editQuestion(Long id, String pytanie, String odp1, String odp2, String odp3, String odp4, Integer prawidlowaOdp) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pytanie z ID=" + id + " nie istnieje.")
        );

        // pytanie
        if (pytanie != null && !pytanie.isEmpty() && !Objects.equals(pytanie, question.getPytanie())) {
            Optional<Question> optionalQuestion = questionRepository.findQuestionByPytanie(pytanie);
            if (optionalQuestion.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W podanym quizie (QuizID="+question.getQuiz_id()+") znajduje się pytanie o takiej samej treści.");
            }
            question.setPytanie(pytanie);
        }

        // odp1
        if (odp1 != null && !odp2.isEmpty() && !Objects.equals(odp1, question.getOdp_1())) {
            String[] odpowiedzi = question.getOdpowiedzi();
            for (int i = 0; i < odpowiedzi.length; ++i)  {
                if (Objects.equals(odpowiedzi[i], odp1)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W pytaniu znajduje się odpowiedź o tej samej treści.");
                }
            }
            question.setOdp_1(odp1);
        }

        // odp2
        if (odp2 != null && !odp2.isEmpty() && !Objects.equals(odp2, question.getOdp_2())) {
            String[] odpowiedzi = question.getOdpowiedzi();
            for (int i = 0; i < odpowiedzi.length; ++i)  {
                if (Objects.equals(odpowiedzi[i], odp2)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W pytaniu znajduje się odpowiedź o tej samej treści.");
                }
            }
            question.setOdp_2(odp2);
        }

        // odp3
        if (odp3 != null && !odp3.isEmpty() && !Objects.equals(odp3, question.getOdp_3())) {
            String[] odpowiedzi = question.getOdpowiedzi();
            for (int i = 0; i < odpowiedzi.length; ++i)  {
                if (Objects.equals(odpowiedzi[i], odp3)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W pytaniu znajduje się odpowiedź o tej samej treści.");
                }
            }
            question.setOdp_3(odp3);
        }

        // odp4
        if (odp4 != null && !odp4.isEmpty() && !Objects.equals(odp4, question.getOdp_4())) {
            String[] odpowiedzi = question.getOdpowiedzi();
            for (int i = 0; i < odpowiedzi.length; ++i)  {
                if (Objects.equals(odpowiedzi[i], odp4)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "W pytaniu znajduje się odpowiedź o tej samej treści.");
                }
            }
            question.setOdp_4(odp4);
        }

        // prawidlowa_odp
        if (prawidlowaOdp != null && prawidlowaOdp >= 1 && prawidlowaOdp <= 4 && !Objects.equals(prawidlowaOdp, question.getPrawidlowa_odp())) {
            question.setPrawidlowa_odp(prawidlowaOdp);
        }
    }

    public void deleteQuestion(Long id) {
        boolean exists = questionRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pytanie z ID=" + id + " nie istnieje.");
        }
        questionRepository.deleteById(id);
    }
}
