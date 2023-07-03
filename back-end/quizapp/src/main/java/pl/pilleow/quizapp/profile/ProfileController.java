package pl.pilleow.quizapp.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path="/{id}")
    public Profile getProfile(@PathVariable("id") Long id) {
        return profileService.getProfile(id);
    }

    @PostMapping(path="/new")
    public void createProfile(@RequestBody Profile profile) {
        profileService.createProfile(profile);
    }

    @PutMapping(path="/{id}/edit")
    public void editProfile(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nazwa_uzytk,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String haslo
    ) {
        profileService.editProfile(id, nazwa_uzytk, email, haslo);
    }

    @DeleteMapping(path="/{id}")
    public void deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
    }
}
