package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
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
import javax.validation.constraints.Max;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieControllerImpl implements MovieController {

    MovieService movieService;

    public MovieControllerImpl(MovieService movieService){
        this.movieService = movieService;
    }


    @Override
    @Operation(summary = "Returns a list of all movies which are stored in the database.", description = "This " +
            "operation returns all movies that are stored in the database in form of MovieDTO. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All movies are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping
    public List<MovieDTO> getAllMovies(){
        return this.movieService.getAllMovies();
    }


    @Override
    @Operation(summary = "Returns all details for a movie.", description = "This operation returns all details" +
            "for the selected movieId in form of the MovieDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The movie details are returned successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/{movieId}")
    public MovieDTO getMovie(@Parameter(description = "The unique movieId")
            @PathVariable("movieId") Long movieId){
        return this.movieService.getMovieById(movieId);
    }


    @Override
    @Operation(summary = "Removes the referenced movie from the database.", description = "This operation " +
            "removes the movie which is referenced in form of the passed movieId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The movie is successfully removed"),
            @ApiResponse(responseCode = "404", description = "The given movieId does not exist in the database"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@Parameter(description = "The unique movieId")
            @Max (0) @PathVariable("movieId") Long movieId){
        try {
            this.movieService.removeMovieById(movieId);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Operation(summary = "Adds a new movie to the database.", description = "This operation " +
            "creates and persists the given movie in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The movie has been successfully added."),
            @ApiResponse(responseCode = "400", description = "The passed movie details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PostMapping
    public MovieDTO addMovie(@Parameter(description = "The new movieDTO which will be stored.")
                                 @Valid @RequestBody MovieDTO movieDTO){
        try {
            return this.movieService.addMovie(movieDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Operation(summary = "Updates a movie in the database with the given input.", description = "This operation " +
            "updates a given movie in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The movie has been successfully changed."),
            @ApiResponse(responseCode = "400", description = "The passed movie details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PutMapping("/{movieId}")
    public MovieDTO updateMovie(@Parameter(description = "The unique movieId")
                                    @PathVariable("movieId") Long movieId,
                                @Parameter(description = "The updated movieDTO")
                                    @Valid @RequestBody MovieDTO movieDTO){
        movieDTO.setId(movieId);
        try {
            return this.movieService.updateMovie(movieDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
