package pl.pilleow.quizapp.profile;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.pilleow.quizapp.quiz.QuizService;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final QuizService quizService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, QuizService quizService) {
        this.profileRepository = profileRepository;
        this.quizService = quizService;
    }

    public Profile getProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile z ID="+id+" nie istnieje.")
        );
        return profile;
    }

    public void createProfile(Profile profile) {
        if (profile.getNazwa_uzytk() == null || profile.getNazwa_uzytk().length() < 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Długość nazwy użytkownika musi być większa lub równa 5.");
        }
        if (profile.getEmail() == null || !profile.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowy email.");
        }
        if (profile.getHaslo() == null || profile.getHaslo().length() < 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Długość hasła musi być większa lub równa 7.");
        }

        Optional<Profile> emailProfile = profileRepository.findProfileByEmail(profile.getEmail());
        if (emailProfile.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email zajęty przez ProfileID="+emailProfile.get().getId()+".");
        }

        Optional<Profile> nameProfile = profileRepository.findProfileByNazwaUzytk(profile.getNazwa_uzytk());
        if (nameProfile.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa użytkownika zajęta przez ProfileID="+nameProfile.get().getId()+".");
        }

        profileRepository.save(profile);
    }

    @Transactional
    public void editProfile(Long id, String nazwa_uzytk, String email, String haslo) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile z ID="+id+" nie istnieje.")
        );

        // email
        if (email != null && !email.isEmpty() && !Objects.equals(email, profile.getEmail())) {
            Optional<Profile> emailProfile = profileRepository.findProfileByEmail(email);
            if (emailProfile.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email zajęty przez ProfileID="+emailProfile.get().getId()+".");
            }
            if (email == null || !email.contains("@")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowy email.");
            }
            profile.setEmail(email);
        }

        // profilename
        if (nazwa_uzytk != null && !nazwa_uzytk.isEmpty() && !Objects.equals(nazwa_uzytk, profile.getNazwa_uzytk())) {
            Optional<Profile> nameProfile = profileRepository.findProfileByNazwaUzytk(nazwa_uzytk);
            if (nameProfile.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa użytkownika zajęta przez ProfileID="+nameProfile.get().getId()+".");
            }
            if (nazwa_uzytk == null || nazwa_uzytk.length() < 5) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Długość nazwy użytkownika musi być większa lub równa 5.");
            }
            profile.setNazwa_uzytk(nazwa_uzytk);
        }

        // haslo
        if (haslo != null && !haslo.isEmpty()) {
            if (haslo == null || haslo.length() < 7) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Długość hasła musi być większa lub równa 7.");
            }
            profile.setHaslo(haslo);
        }
    }

    @Transactional
    public void deleteProfile(Long id) {
        boolean exists = profileRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile z ID="+id+" nie istnieje.");
        }
        quizService.deleteQuizzesByAuthorId(id);
        profileRepository.deleteById(id);
    }
}
