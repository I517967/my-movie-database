package de.dhbw.ravensburg.wp.mymoviedatabase.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String title;

    private double duration;

    private LocalDate premiereDate;

    private String description;

    private String trailerUrl;

    private String coverImgUrl;

    private double imdbRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="director_id", nullable = true) //die Frendschlüsselspalte heißt director_id, das Gegenstück auf Seite der Referenz heißt director,
    private Director director; //das hier (director) wird bei director referenziert


    @ManyToMany(cascade = CascadeType.ALL) //Tabellendef:
    @JoinTable(name = "movie_cast",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id",
                            updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "cast_id", referencedColumnName = "id", //"cast_id" ist der name der Spalte in der neuerstellten Tabelle
                            updatable = false)})
    private List<Cast> involvedCast; //referenziert nur den Attributsnamen des Eigners, ist sozusagen die Gegenseite der Referenz


    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL) //haben hier bei movie das mappedBy stehen, d.h. in dem Fall ist der Soundtrack der Eigner, nicht der Movie
    private Soundtrack soundtrack;


    public Movie(String title, double duration, LocalDate premiereDate,
                 String description, String trailerUrl, String coverImgUrl, double imdbRating ) {

        this.title = title;
        this.duration = duration;
        this.premiereDate = premiereDate;
        this.description = description;
        this.trailerUrl = trailerUrl;
        this.coverImgUrl = coverImgUrl;
        this.imdbRating = imdbRating;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Award> awards;

}
