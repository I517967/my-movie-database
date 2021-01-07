package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;

import java.util.List;

public interface AwardService {
    Award addAward(Award award);
    Award getAwardById(long id);
    List<Award> getAllAwards();
    Award updateAward(Award award);
    void deleteAward(long id);
}
