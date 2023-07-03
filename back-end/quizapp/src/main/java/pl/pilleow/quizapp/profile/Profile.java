package pl.pilleow.quizapp.profile;

import jakarta.persistence.*;

@Entity
@Table
public class Profile {
    @Id
    @SequenceGenerator(
            name="profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="profile_sequence"
    )
    private Long id;
    private String haslo;
    private String email;
    private String nazwa_uzytk;

    public Profile(Long id, String haslo, String email, String nazwa_uzytk) {
        this.id = id;
        this.haslo = haslo;
        this.email = email;
        this.nazwa_uzytk = nazwa_uzytk;
    }

    public Profile(String haslo, String email, String nazwa_uzytk) {
        this.haslo = haslo;
        this.email = email;
        this.nazwa_uzytk = nazwa_uzytk;
    }

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getEmail() {
        return email;
    }

    public String getNazwa_uzytk() {
        return nazwa_uzytk;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNazwa_uzytk(String nazwa_uzytk) {
        this.nazwa_uzytk = nazwa_uzytk;
    }
}
