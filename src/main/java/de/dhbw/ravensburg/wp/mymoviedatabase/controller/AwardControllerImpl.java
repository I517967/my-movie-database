package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.AwardService;
    import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/awards")

public class AwardControllerImpl implements AwardController {

    private AwardService awardService;

    public AwardControllerImpl(AwardService awardService) {
        this.awardService = awardService;
    }

    @Override
    @GetMapping
    public List<Award> getAllAwards() {
        return this.awardService.getAllAwards();
    }

    @Override
    @PostMapping
    public Award addAward(@RequestBody Award award) {
        return this.awardService.addAward(award);
    }

    @Override
    @PutMapping("/{awardId}")
    public Award updateAward(@PathVariable("awardId") Long awardId, @RequestBody Award award) {
        award.setId(awardId);
        return this.awardService.updateAward(award);
    }

    @Override
    @DeleteMapping("/{awardId}")
    public void deleteAward(@PathVariable("awardId") Long awardId) {
        this.awardService.removeById(awardId);

    }

    @Override
    @GetMapping("/{awardId")
    public List<Award> getAwards() {
        return this.awardService.getAllAwards();

    }
}



