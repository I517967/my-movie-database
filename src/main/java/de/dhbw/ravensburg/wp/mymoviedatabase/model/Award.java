package de.dhbw.ravensburg.wp.mymoviedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Award {
    @Id
    @GeneratedValue
    private long id;
    private String academy;
    private String category;
    private int year;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("awards")
    private Movie movie;

    public Award(String academy, String category, int year){
        this.academy=academy;
        this.category=category;
        this.year=year;
    }

}
