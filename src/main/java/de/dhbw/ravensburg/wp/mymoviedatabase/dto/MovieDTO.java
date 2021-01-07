package de.dhbw.ravensburg.wp.mymoviedatabase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

    private Long id;

    private String title;

    @Min(0)
    @Max(1000)
    private double duration;

    private String description;

    @NotBlank
    private String trailerUrl;

    @NotBlank
    private String coverImgUrl;

    @Min(0)
    private double imdbRating;

    private List<Long> castIds;

    //private DirectorDTO director;

    private List<AwardDTO> awards;

    public MovieDTO(Long id, String title, double duration, String description, String trailerUrl, String coverImgUrl,
                    double imdbRating, List<Long> castIds, List<AwardDTO> awards){
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.trailerUrl = trailerUrl;
        this.coverImgUrl = coverImgUrl;
        this.imdbRating = imdbRating;
        this.castIds = castIds;
        this.awards = awards;
    }

}
