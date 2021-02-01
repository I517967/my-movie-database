package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.AwardRepository;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
    public class AwardServiceImpl implements AwardService{

        @Autowired
        private AwardRepository awardRepository;

        @Autowired
        private MovieRepository movieRepository;

        @Override
        public void addAward(Award award){
            //Movie tmp = this.movieRepository.findById(award.getMovie().getId().get());
            //award.setMovie(tmp);
            this.awardRepository.save(award);


    }
}
