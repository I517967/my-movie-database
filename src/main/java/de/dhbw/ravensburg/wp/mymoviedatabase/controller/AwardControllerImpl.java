package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.AwardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Returns a list of all awards which are stored in the database.", description = "This " +
            "operation returns all awards that are stored in the database in form of AwardDTO. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All awards are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping
    public List<AwardDTO> getAllAwards(){
        return this.awardService.getAllAwards();
    }

    @Override
    @Operation(summary = "Returns all details for an award.", description = "This operation returns all details" +
            "for the selected awardId in form of the AwardDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The award details are returned successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/{awardId}")
    public AwardDTO getAwardByOd(@Parameter(description = "The unique awardId")
                                     @PathVariable("awardId") long awardId){
        return this.awardService.getAwardById(awardId);
    }

    @Override
    @Operation(summary = "Removes the referenced award from the database.", description = "This operation " +
            "removes the award which is referenced in form of the passed awardId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The award is successfully removed"),
            @ApiResponse(responseCode = "404", description = "The given awardId does not exist in the database"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @DeleteMapping("/{awardId}")
    public void removeAwardById(@Parameter(description = "The unique awardId")
                                    @PathVariable("awardId") long awardId){
        try {
            this.awardService.removeAwardById(awardId);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    @Operation(summary = "Adds a new award to the database.", description = "This operation " +
            "creates and persists the given award in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The award has been successfully added."),
            @ApiResponse(responseCode = "400", description = "The passed award details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    public AwardDTO addAward(@Parameter(description = "The new awardDTO which will be stored.")
                                 @Valid @RequestBody AwardDTO awardDTO){
        try {
            return this.awardService.addAward(awardDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping("/{awardId}")
    @Operation(summary = "Updates the award in the database with the given input.", description = "This operation " +
            "updates a given award in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The award has been successfully changed."),
            @ApiResponse(responseCode = "400", description = "The passed award details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    public AwardDTO updateAward(@Parameter(description = "The unique awardId")
                                    @PathVariable("awardId") Long awardId,
                                @Parameter(description = "The updated awardDTO")
                                    @Valid @RequestBody AwardDTO awardDTO){
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
