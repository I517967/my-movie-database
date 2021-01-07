package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;

import java.util.List;

public interface AwardController {

    List<AwardDTO> getAllAwards();
    AwardDTO getAwardById(Long awardId);
    void removeAwardById(Long awardId);
    AwardDTO addAward(AwardDTO awardDTO);
    AwardDTO updateAward(Long awardId,AwardDTO awardDTO);
}
