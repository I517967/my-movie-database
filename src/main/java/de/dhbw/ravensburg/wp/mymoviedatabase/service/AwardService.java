package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;

import javax.transaction.Transactional;

public interface AwardService {

    void addAward(Award award);
}
