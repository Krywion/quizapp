package pl.pilleow.quizapp.question;

import jakarta.persistence.*;

@Entity
@Table
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    private Long id;
    private Long quiz_id;
    private String odp_1;
    private String odp_2;
    private String odp_3;
    private String odp_4;
    private Integer prawidlowa_odp;
    private String pytanie;

    public Question() {
    }

    public Question(Long quiz_id,
                    String odp_1,
                    String odp_2,
                    String odp_3,
                    String odp_4,
                    Integer prawidlowa_odp,
                    String pytanie,
                    Long id) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.odp_1 = odp_1;
        this.odp_2 = odp_2;
        this.odp_3 = odp_3;
        this.odp_4 = odp_4;
        this.prawidlowa_odp = prawidlowa_odp;
        this.pytanie = pytanie;
    }

    public Question(Long quiz_id,
                    String ans_1,
                    String ans_2,
                    String ans_3,
                    String ans_4,
                    Integer prawidlowa_odp,
                    String question) {
        this.quiz_id = quiz_id;
        this.odp_1 = ans_1;
        this.odp_2 = ans_2;
        this.odp_3 = ans_3;
        this.odp_4 = ans_4;
        this.prawidlowa_odp = prawidlowa_odp;
        this.pytanie = question;
    }

    public String[] getOdpowiedzi() {
        return new String[] {this.odp_1, this.odp_2, this.odp_3, this.odp_4};
    }

    public Long getId() {
        return id;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public String getOdp_1() {
        return odp_1;
    }

    public String getOdp_2() {
        return odp_2;
    }

    public String getOdp_3() {
        return odp_3;
    }

    public String getOdp_4() {
        return odp_4;
    }

    public Integer getPrawidlowa_odp() {
        return prawidlowa_odp;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setOdp_1(String ans_1) {
        this.odp_1 = ans_1;
    }

    public void setOdp_2(String ans_2) {
        this.odp_2 = ans_2;
    }

    public void setOdp_3(String ans_3) {
        this.odp_3 = ans_3;
    }

    public void setOdp_4(String ans_4) {
        this.odp_4 = ans_4;
    }

    public void setPrawidlowa_odp(Integer correct_ans) {
        this.prawidlowa_odp = correct_ans;
    }

    public void setPytanie(String question) {
        this.pytanie = question;
    }
}
