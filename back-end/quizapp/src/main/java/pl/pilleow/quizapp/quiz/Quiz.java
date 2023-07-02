package pl.pilleow.quizapp.quiz;

import jakarta.persistence.*;

@Entity
@Table
public class Quiz {
    @Id
    @SequenceGenerator(
            name="quiz_sequence",
            sequenceName = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="quiz_sequence"
    )
    private Long id;
    private Long author_id;
    private String opis;
    private String tytul;

    public Quiz(Long author_id, String opis, String tytul) {
        this.author_id = author_id;
        this.opis = opis;
        this.tytul = tytul;
    }

    public Quiz(Long id, Long author_id, String opis, String tytul) {
        this.id = id;
        this.author_id = author_id;
        this.opis = opis;
        this.tytul = tytul;
    }

    public Quiz() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public String getOpis() {
        return opis;
    }

    public String getTytul() {
        return tytul;
    }
}
