package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.mapper.AwardMapper;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.AwardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardRepository awardRepository;

    private AwardMapper awardMapper;

    public AwardServiceImpl(AwardRepository awardRepository, AwardMapper awardMapper){
        this.awardRepository = awardRepository;
        this.awardMapper = awardMapper;
    }

    @Override
    public AwardDTO addAward(AwardDTO awardDTO){
        Award tmp = this.awardMapper.awardDTOToAward(awardDTO);
        this.awardRepository.save(tmp);
        return this.awardMapper.awardToAwardDTO(tmp);
    }
    @Override
    public AwardDTO getAwardById(long id){
        Award tmp = this.awardRepository.findById(id).get();
        return this.awardMapper.awardToAwardDTO(tmp);
    }
    @Override
    public List<AwardDTO> getAllAwards(){
        return this.awardMapper.awardsToAwardsDTO(this.awardRepository.findAll());
    }

    @Override
    public AwardDTO updateAward(AwardDTO awardDTO){
        Award tmp = this.awardMapper.awardDTOToAward(awardDTO);
        this.awardRepository.save(tmp);
        return this.awardMapper.awardToAwardDTO(tmp);
    }

    public void removeAwardById(long id){
        this.awardRepository.deleteById(id);
    }


}
