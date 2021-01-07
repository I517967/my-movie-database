package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;

import java.util.List;

public interface AwardService {
    AwardDTO addAward(AwardDTO awardDTO);
    AwardDTO getAwardById(long id);
    List<AwardDTO> getAllAwards();
    AwardDTO updateAward(AwardDTO awardDTO);
    void removeAwardById(long id);
}
