package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.AwardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/awards")
public class AwardControllerImpl implements AwardController {

    private AwardService awardService;

    public AwardControllerImpl(AwardService awardService){
        this.awardService = awardService;
    }

    @Override
    @GetMapping
    public List<AwardDTO> getAllAwards(){
        return this.awardService.getAllAwards();
    }

    @Override
    @GetMapping("/{awardId}")
    public AwardDTO getAwardById(@PathVariable("awardId") Long awardId){
        return this.awardService.getAwardById(awardId);
    }

    @Override
    @DeleteMapping("/{awardId}")
    public void removeAwardById(@PathVariable("awardId") Long awardId){
        try {
            this.awardService.removeAwardById(awardId);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    public AwardDTO addAward(@Valid @RequestBody AwardDTO awardDTO){
        try {
            return this.awardService.addAward(awardDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping("/{awardId}")
    public AwardDTO updateAward(@PathVariable("awardId") Long awardId, @Valid @RequestBody AwardDTO awardDTO){
        awardDTO.setId(awardId);
        try {
            return this.awardService.updateAward(awardDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
