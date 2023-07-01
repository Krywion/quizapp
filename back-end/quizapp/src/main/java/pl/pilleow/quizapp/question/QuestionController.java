package pl.pilleow.quizapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path="/{id}")
    public Question getQuestion(@PathVariable("id") Long id) {
        return questionService.getQuestion(id);
    }

    @PostMapping(path="/new")
    public void createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
    }

    @PutMapping(path="/{id}/edit")
    public void editQuestion(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String pytanie,
            @RequestParam(required = false) String odp_1,
            @RequestParam(required = false) String odp_2,
            @RequestParam(required = false) String odp_3,
            @RequestParam(required = false) String odp_4,
            @RequestParam(required = false) Integer prawidlowa_odp
    ) {
        questionService.editQuestion(id, pytanie, odp_1, odp_2, odp_3, odp_4, prawidlowa_odp);
    }

    @DeleteMapping(path="/{id}")
    public void deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
    }
}
