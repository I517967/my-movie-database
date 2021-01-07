package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AwardController {

    List<AwardDTO> getAllAwards();
    AwardDTO getAwardByOd(@Parameter(description = "The unique awardId")
                          @PathVariable("awardId") long awardId);
    void removeAwardById(@Parameter(description = "The unique awardId")
                         @PathVariable("awardId") long awardId);
    AwardDTO addAward(@Parameter(description = "The new awardDTO which will be stored.")
                      @Valid @RequestBody AwardDTO awardDTO);
    AwardDTO updateAward(@Parameter(description = "The unique awardId")
                         @PathVariable("awardId") Long awardId,
                         @Parameter(description = "The updated awardDTO")
                         @Valid @RequestBody AwardDTO awardDTO);
}
