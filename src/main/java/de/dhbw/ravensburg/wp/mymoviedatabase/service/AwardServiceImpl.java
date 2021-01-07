package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.AwardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardRepository awardRepository;

    public AwardServiceImpl (AwardRepository awardRepository){
        this.awardRepository = awardRepository;
    }

    @Override
    public AwardDTO addAward(AwardDTO awardDTO){
        Award tmp = new Award(awardDTO.getAcademy(), awardDTO.getCategory(), awardDTO.getCelebrationYear());
        this.awardRepository.save(tmp);
        return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
    }
    @Override
    public AwardDTO getAwardById(long id){
        Award tmp = this.awardRepository.findById(id).get();
        return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
    }
    @Override
    public List<AwardDTO> getAllAwards(){
        List<Award> tmp = this.awardRepository.findAll();
        List<AwardDTO> result = new ArrayList<>();
        for(Award award:tmp){
            result.add(new AwardDTO(award.getId(), award.getAcademy(), award.getCategory(), award.getYear()));
        }
        return result;
    }

    @Override
    public AwardDTO updateAward(AwardDTO awardDTO){
        Award tmp = new Award(awardDTO.getAcademy(), awardDTO.getCategory(), awardDTO.getCelebrationYear());
        this.awardRepository.save(tmp);
        return new AwardDTO(tmp.getId(), tmp.getAcademy(), tmp.getCategory(), tmp.getYear());
    }

    public void removeAwardById(long id){
        this.awardRepository.deleteById(id);
    }


}
