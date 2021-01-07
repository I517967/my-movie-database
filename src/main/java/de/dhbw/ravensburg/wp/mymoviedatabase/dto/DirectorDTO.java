package de.dhbw.ravensburg.wp.mymoviedatabase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DirectorDTO {
    private String forename;
    private String surname;
    private LocalDate dateOfBirth;
    private String profileImgUrl;
}
