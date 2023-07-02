package pl.pilleow.quizapp.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path="/{id}")
    public Quiz getQuiz(@PathVariable("id") Long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping(path="/new")
    public void createQuiz(@RequestBody Quiz quiz) {
        quizService.createQuiz(quiz);
    }

    @PutMapping(path="/{id}/edit")
    public void editQuiz(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String tytul,
            @RequestParam(required = false) String opis,
            @RequestParam(required = false) Long author_id
    ) {
        quizService.editQuiz(id, tytul, opis, author_id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteQuiz(@PathVariable("id") Long id) {
        quizService.deleteQuiz(id);
    }
}
