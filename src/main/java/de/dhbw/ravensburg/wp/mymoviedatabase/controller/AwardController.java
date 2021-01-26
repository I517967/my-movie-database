package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AwardController {
    @GetMapping
    List<Award> getAllAwards();

    @PostMapping
    Award addAward(@RequestBody Award award);

    @PutMapping("/{awardId}")
    Award updateAward(@PathVariable("awardId") Long awardId, @RequestBody Award award);

    @DeleteMapping("/{awardId}")
    void deleteAward(@PathVariable("awardId") Long awardId);

    @GetMapping("/{awardId")
    public List<Award> getAllAwards();

}


