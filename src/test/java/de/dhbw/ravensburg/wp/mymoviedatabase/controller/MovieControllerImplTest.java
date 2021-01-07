package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieControllerImpl.class)
public class MovieControllerImplTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Test Entities
    private List<MovieDTO> movieList;
    private MovieDTO movie_1;
    private MovieDTO movie_2;

    private final String controllerPath = "/api/v1/movies";

    @BeforeEach
    public void setUp(){

        AwardDTO oscar_1 = new AwardDTO(1L, "Oscar", "Best Picture", 2008);
        AwardDTO oscar_2 = new AwardDTO(2L, "Oscar", "Best Performance by an Actor in a Supporting Role", 2009);
        AwardDTO oscar_3 = new AwardDTO(3L,"Oscar", "Best Achievement in Sound Editing", 2009);

        movie_1 = new MovieDTO(1L,
                "Interstellar",
                2.49,
                "A team of explorers travel through space.",
                "https://imdb.interstellar.test",
                "https://wikipedia.interstellar.test.jpeg",
                8.6,
                Arrays.asList(4L),
                Arrays.asList(oscar_1));

        movie_2 = new MovieDTO(2L,
                "The Dark Knight",
                2.32,
                "Joker vs. Batman",
                "https://imdb.darkknight.test",
                "https://wikipedia.darkknight.test.jpeg",
                9.0,
                Arrays.asList(1L,2L),
                Arrays.asList(oscar_2, oscar_3));

        this.movieList = Arrays.asList(movie_1, movie_2);
    }


    @Test
    public void shouldReturnAllMovies() throws Exception{
        when(movieService.getAllMovies()).thenReturn(movieList);

        this.mockMvc.perform(get(controllerPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(movie_1.getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(movie_1.getTitle())))
                .andExpect(jsonPath("$[0].duration", is(movie_1.getDuration())))
                .andExpect(jsonPath("$[0].description", is(movie_1.getDescription())))
                .andExpect(jsonPath("$[0].trailerUrl", is(movie_1.getTrailerUrl())))
                .andExpect(jsonPath("$[0].coverImgUrl", is(movie_1.getCoverImgUrl())))
                .andExpect(jsonPath("$[0].imdbRating", is(movie_1.getImdbRating())))
                .andExpect(jsonPath("$[0].castIds", containsInAnyOrder(movie_1.getCastIds().get(0).intValue())))
                .andExpect(jsonPath("$[0].awards[0].id", is(movie_1.getAwards().get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].awards[0].academy", is(movie_1.getAwards().get(0).getAcademy())))
                .andExpect(jsonPath("$[0].awards[0].category", is(movie_1.getAwards().get(0).getCategory())))
                .andExpect(jsonPath("$[0].awards[0].celebrationYear", is(movie_1.getAwards().get(0).getCelebrationYear())))
                .andDo(print());

        verify(movieService).getAllMovies();
    }

    @Test
    public void shouldReturnMovie() throws Exception{
        when(movieService.getMovieById(1L)).thenReturn(movie_1);

        this.mockMvc.perform(get(controllerPath+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(9)))
                .andExpect(jsonPath("$.id", is(movie_1.getId().intValue())))
                .andExpect(jsonPath("$.title", is(movie_1.getTitle())))
                .andExpect(jsonPath("$.duration", is(movie_1.getDuration())))
                .andExpect(jsonPath("$.description", is(movie_1.getDescription())))
                .andExpect(jsonPath("$.trailerUrl", is(movie_1.getTrailerUrl())))
                .andExpect(jsonPath("$.coverImgUrl", is(movie_1.getCoverImgUrl())))
                .andExpect(jsonPath("$.imdbRating", is(movie_1.getImdbRating())))
                .andExpect(jsonPath("$.castIds", containsInAnyOrder(movie_1.getCastIds().get(0).intValue())))
                .andExpect(jsonPath("$.awards[0].id", is(movie_1.getAwards().get(0).getId().intValue())))
                .andExpect(jsonPath("$.awards[0].academy", is(movie_1.getAwards().get(0).getAcademy())))
                .andExpect(jsonPath("$.awards[0].category", is(movie_1.getAwards().get(0).getCategory())))
                .andExpect(jsonPath("$.awards[0].celebrationYear", is(movie_1.getAwards().get(0).getCelebrationYear())))
                .andDo(print());
        verify(movieService).getMovieById(1L);
    }

    @Test
    public void shouldRemoveMovie() throws Exception{
         doNothing().when(movieService).removeMovieById(1L);
         this.mockMvc.perform(delete(controllerPath+"/1"))
                 .andExpect(status().isOk());
         verify(movieService).removeMovieById(1L);
    }

    @Test
    public void shouldAddMovie() throws Exception{
        when(movieService.addMovie(any())).thenReturn(movie_2);

        this.mockMvc.perform(post(controllerPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(movie_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(movie_2.getId().intValue())));

        verify(movieService).addMovie(any());
    }


    @Test
    public void shouldUpdateMovie() throws Exception{
        when(movieService.updateMovie(any())).thenReturn(movie_2);
        this.mockMvc.perform(put(controllerPath+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(movie_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(movie_2.getId().intValue())));
        verify(movieService).updateMovie(any());
    }




}
