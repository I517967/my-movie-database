package de.dhbw.ravensburg.wp.mymoviedatabase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AwardDTO {
    private Long id;
    private String academy;
    private String category;
    private int celebrationYear;

    public AwardDTO(Long id, String academy, String category, int celebrationYear){
        this.id = id;
        this.academy = academy;
        this.category = category;
        this.celebrationYear = celebrationYear;
    }

}
