package de.dhbw.ravensburg.wp.mymoviedatabase.mapper;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.DirectorDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Cast;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Director;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "castIds", source = "involvedCast")
    MovieDTO movieToMovieDTO(Movie movie);
    List<MovieDTO> moviesToMovieDTOs(List<Movie> movies);

    DirectorDTO directorToDirectorDTO(Director director);
    List<DirectorDTO> directorsToDirectorDTOs(List<Director> directors);


    default Long map(Cast cast){
        return cast.getId();
    }

    Movie movieDTOToMovie(MovieDTO movieDTO);
}
